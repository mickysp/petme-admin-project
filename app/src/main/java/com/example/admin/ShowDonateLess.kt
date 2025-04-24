package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.databinding.ActivityShowDonateLessBinding
import com.example.admin.databinding.ActivityShowPetIsadoptBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowDonateLess : AppCompatActivity() {
    private lateinit var bindingDonateless: ActivityShowDonateLessBinding
    var Donate = arrayListOf<Donate>()
    val donateClient = DonateAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDonateless = ActivityShowDonateLessBinding.inflate(layoutInflater)
        setContentView(bindingDonateless.root)

        bindingDonateless.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        bindingDonateless.recyclerView.addItemDecoration(
            DividerItemDecoration(
                bindingDonateless.recyclerView.getContext(), DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        callPetData()
    }
    fun callPetData(){
        Donate.clear();
        donateClient.showDonateLess().enqueue(object : Callback<List<Donate>> {
            override fun onResponse(call: Call<List<Donate>>,
                                    response: Response<List<Donate>>
            ) {

                response.body()?.forEach {
                    Donate.add(Donate(
                        it.donate_id,
                        it.user_id,
                        it.picture_pay,
                        it.balance,
                        it.last4digits,
                        it.username,
                        it.tel,
                        it.address
                    ))
                }
//// Set Data to RecyclerRecyclerView
                bindingDonateless.recyclerView.adapter = ShowDonateLessAdapter(Donate,applicationContext) }
            override fun onFailure(call: Call<List<Donate>>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}