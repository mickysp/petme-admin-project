package com.example.admin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class totalMoney(
    @Expose
    @SerializedName("Total") val balance: Int
)
