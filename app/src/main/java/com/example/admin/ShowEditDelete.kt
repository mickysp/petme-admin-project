package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.databinding.ActivityShowEditDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowEditDelete : AppCompatActivity() {
    private lateinit var bindingShow : ActivityShowEditDeleteBinding
    var Pet = arrayListOf<PetNoIsadotp>()
    val PetClient = PetAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingShow = ActivityShowEditDeleteBinding.inflate(layoutInflater)
        setContentView(bindingShow.root )

        bindingShow.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        bindingShow.recyclerView.addItemDecoration(DividerItemDecoration(
            bindingShow.recyclerView.getContext(), DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        callPetData()
    }
    fun callPetData(){
        Pet.clear();
        PetClient.noIsadopt().enqueue(object : Callback<List<PetNoIsadotp>> {
            override fun onResponse(call: Call<List<PetNoIsadotp>>,
                                    response: Response<List<PetNoIsadotp>>) {

                response.body()?.forEach {
                    Pet.add(PetNoIsadotp(it.petdetail_id, it.delete_status,it.picture_pet,it.old,it.month,it.district,it.isadopt,it.admin_id,it.detail,it.name,it.typeanimal,it.gender,it.status_pet))
                }
                bindingShow.recyclerView.adapter = EditDeleteAdapter(Pet,applicationContext) }
            override fun onFailure(call: Call<List<PetNoIsadotp>>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}