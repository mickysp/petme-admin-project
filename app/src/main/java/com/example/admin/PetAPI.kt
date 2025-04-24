package com.example.admin

    import okhttp3.MultipartBody
    import okhttp3.RequestBody
    import retrofit2.Call
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory
    import retrofit2.http.*

    interface PetAPI {
        @GET("allpet")
        fun retrievePet(): Call<List<Pet>>

        @GET("petNoIsadopt")
        fun noIsadopt(): Call<List<PetNoIsadotp>>

        @GET("petIsadopt")
        fun Isadopt(): Call<List<Pet>>

        @FormUrlEncoded
        @POST("pet")
        fun insertPet(
            @Field("name") name: String,
            @Field("gender") gender: String,
            @Field("old") old: Int,
            @Field("month") month: Int,
            @Field("status_pet") status_pet: String,
            @Field("typeanimal") typeanimal: String,
            @Field("district") district: String,
            @Field("detail") detail: String,
            @Field("picture_pet") picture_pet:String
        ): Call<Pet>



        @Multipart
        @POST("insertPost")
        fun imagePet(
            @Part image: MultipartBody.Part,
            @Part("name") name:RequestBody,
            @Part("gender") gender:RequestBody,
            @Part("old") old:RequestBody,
            @Part("month") month: RequestBody,
            @Part("status_pet") status_pet: RequestBody,
            @Part("typeanimal") typeanimal:RequestBody,
            @Part("district") district: RequestBody,
            @Part("detail") detail:RequestBody
        ): Call<Pet>

        @GET("pet/{id}")
        fun retrievePetID(
            @Path("id") id : Int
        ): Call<Pet>

        @Multipart /// Update and Upload Image
        @PUT("edit/{petdetail_id}")
        fun updatePets(
            @Part image: MultipartBody.Part,
            @Part("name") name:RequestBody,
            @Part("gender") gender:RequestBody,
            @Part("old") old:RequestBody,
            @Part("month") month: RequestBody,
            @Part("status_pet") status_pet: RequestBody,
            @Part("typeanimal") typeanimal:RequestBody,
            @Part("district") district: RequestBody,
            @Part("detail") detail:RequestBody ): Call<Pet>

        @FormUrlEncoded /// Update
        @PUT("editPet/{petdetail_id}")
        fun updatePet(
            @Path("petdetail_id") petdetail_id: Int,
            @Field("name") name: String,
            @Field("gender") gender: String,
            @Field("old") old: Int,
            @Field("month") month: Int,
            @Field("status_pet") status_pet: String,
            @Field("typeanimal") typeanimal: String,
            @Field("district") district: String,
            @Field("detail") detail: String,
            @Field("picture_pet") picture_pet:String
        ): Call<Pet>

        @PUT("delete_soft/{petdetail_id}") /// Soft Delete
        fun softDeletePet(
            @Path("petdetail_id") petdetail_id: Int
        ): Call<Pet>

        @DELETE("pet/{id}")
        fun deletePet(
            @Path("id") id: Int
        ): Call<Pet>

    companion object {
        fun create(): PetAPI {
            val petClient: PetAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PetAPI::class.java)
            return petClient
        }
    }
}