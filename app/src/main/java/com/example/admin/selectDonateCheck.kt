package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin.databinding.ActivitySelectDonateCheckBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class selectDonateCheck : AppCompatActivity() {
    private lateinit var bindingSelectDonate: ActivitySelectDonateCheckBinding
    var donateList = arrayListOf<totalMoney>()
    val createClient = DonateAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSelectDonate = ActivitySelectDonateCheckBinding.inflate(layoutInflater)
        setContentView(bindingSelectDonate.root)

        bindingSelectDonate.lessThan.setOnClickListener {
            val intent = Intent(applicationContext,ShowDonateLess::class.java)
            startActivity(intent)
        }
        bindingSelectDonate.moreThan.setOnClickListener {
            val intent = Intent(applicationContext,ShowDonateMore::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        callPetData()
    }

    fun callPetData(){
        createClient.totalMoney().enqueue(object : Callback<List<totalMoney>> {
            override fun onResponse(call: Call<List<totalMoney>>,
                                    response: Response<List<totalMoney>>
            ) {
                response.body()?.forEach {

                    bindingSelectDonate.total.text = totalMoney(it.balance).balance.toString()
                }
            }

            override fun onFailure(call: Call<List<totalMoney>>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}