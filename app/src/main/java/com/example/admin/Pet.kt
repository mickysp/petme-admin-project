package com.example.admin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pet(
    @Expose
    @SerializedName("petdetail_id") val petdetail_id: Int,

    @Expose
    @SerializedName("delete_status") val delete_status: String,

    @Expose
    @SerializedName("picture_pet") val picture_pet: String,

    @Expose
    @SerializedName("old") val old: Int,

    @Expose
    @SerializedName("month") val month: Int,

    @Expose
    @SerializedName("district") val district: String,

    @Expose
    @SerializedName("user_id") val user_id: Int,

    @Expose
    @SerializedName("isadopt") val isadopt: String,

    @Expose
    @SerializedName("admin_id") val admin_id: Int,

    @Expose
    @SerializedName("detail") val detail: String,

    @Expose
    @SerializedName("name") val name: String,

    @Expose
    @SerializedName("typeanimal") val typeanimal: String,

    @Expose
    @SerializedName("gender") val gender: String,

    @Expose
    @SerializedName("status_pet") val status_pet: String,

    @Expose
    @SerializedName("username") val username: String,

    @Expose
    @SerializedName("address") val address: String

){
}