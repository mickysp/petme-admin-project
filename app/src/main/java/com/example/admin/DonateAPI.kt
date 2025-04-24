package com.example.admin

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DonateAPI {
    @GET("queryDonateLess")
    fun showDonateLess(): Call<List<Donate>>

    @GET("queryDonateMore")
    fun showDonateMore(): Call<List<Donate>>

    @GET("allDonate")
    fun totalMoney(): Call<List<totalMoney>>

    companion object {
        fun create(): DonateAPI {
            val donateClient: DonateAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DonateAPI::class.java)
            return donateClient
        }
    }
}