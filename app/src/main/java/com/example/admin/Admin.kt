package com.example.admin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Admin(
    @Expose
    @SerializedName("admin_id") val admin_id: Int,

    @Expose
    @SerializedName("username") val username: String,

    @Expose
    @SerializedName("password") val password: String
)
