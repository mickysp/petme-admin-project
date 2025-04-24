package com.example.admin

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AdminAPI {
    @GET("alladmin")
    fun retrieveAdmin(): Call<List<Admin>>

    @GET("alluser")
    fun retrieveuser(): Call<List<User>>

    @GET("queryVerify")
    fun userVerify(): Call<List<User>>

    @FormUrlEncoded
    @POST("loginadmin")
    fun loginadmin (@Field("username") username:String,
                   @Field("password") password:String): Call<Admin>

    @PUT("successVerify/{user_id}")
    fun successVerify(
        @Path("user_id") user_id: Int
    ): Call<User>

    companion object
    {
        fun create(): AdminAPI
        {
            val userClient : AdminAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AdminAPI ::class.java)
            return userClient
        }
    }
}