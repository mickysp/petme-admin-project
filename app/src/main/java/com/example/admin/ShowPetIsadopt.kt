package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.databinding.ActivityShowPetIsadoptBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowPetIsadopt : AppCompatActivity() {
    private lateinit var bindingIsadopt: ActivityShowPetIsadoptBinding
    var Pet = arrayListOf<Pet>()
    val PetClient = PetAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingIsadopt = ActivityShowPetIsadoptBinding.inflate(layoutInflater)
        setContentView(bindingIsadopt.root)

        bindingIsadopt.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        bindingIsadopt.recyclerView.addItemDecoration(
            DividerItemDecoration(
                bindingIsadopt.recyclerView.getContext(), DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        callPetData()
    }
    fun callPetData(){
        Pet.clear();
        PetClient.Isadopt().enqueue(object : Callback<List<Pet>> {
            override fun onResponse(call: Call<List<Pet>>,
                                    response: Response<List<Pet>>
            ) {

                response.body()?.forEach {
                    Pet.add(Pet(it.petdetail_id, it.delete_status,it.picture_pet,it.old,it.month,it.district,it.user_id,it.isadopt,it.admin_id,it.detail,it.name,it.typeanimal,it.gender,it.status_pet,it.username,it.address))
                }
//// Set Data to RecyclerRecyclerView
                bindingIsadopt.recyclerView.adapter = ShowPetIsadoptAdapter(Pet,applicationContext) }
            override fun onFailure(call: Call<List<Pet>>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}