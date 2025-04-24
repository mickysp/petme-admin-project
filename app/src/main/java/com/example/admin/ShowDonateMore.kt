package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.databinding.ActivityShowDonateLessBinding
import com.example.admin.databinding.ActivityShowDonateMoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowDonateMore : AppCompatActivity() {
    private lateinit var bindingDonatemore: ActivityShowDonateMoreBinding
    var Donate = arrayListOf<Donate>()
    val donateClient = DonateAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDonatemore = ActivityShowDonateMoreBinding.inflate(layoutInflater)
        setContentView(bindingDonatemore.root)

        bindingDonatemore.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        bindingDonatemore.recyclerView.addItemDecoration(
            DividerItemDecoration(
                bindingDonatemore.recyclerView.getContext(), DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        callPetData()
    }
    fun callPetData(){
        Donate.clear();
        donateClient.showDonateMore().enqueue(object : Callback<List<Donate>> {
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
                bindingDonatemore.recyclerView.adapter = ShowDonateMoreAdapter(Donate,applicationContext) }
            override fun onFailure(call: Call<List<Donate>>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}