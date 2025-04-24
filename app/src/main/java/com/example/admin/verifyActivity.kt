package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.databinding.ActivityVerifyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class verifyActivity : AppCompatActivity() {
    private lateinit var bindingVerify: ActivityVerifyBinding
    var User = arrayListOf<User>()
    val userClient = AdminAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingVerify = ActivityVerifyBinding.inflate(layoutInflater)
        setContentView(bindingVerify.root)

        bindingVerify.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        bindingVerify.recyclerView.addItemDecoration(
            DividerItemDecoration(
                bindingVerify.recyclerView.getContext(), DividerItemDecoration.VERTICAL)
        )
    }
    override fun onResume() {
        super.onResume()
        callUserData()
    }
    fun callUserData(){
        User.clear()
        userClient.userVerify().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>,
                                    response: Response<List<User>>
            ) {

                response.body()?.forEach {
                    User.add(User(
                        it.user_id,
                        it.username,
                        it.password,
                        it.email,
                        it.address,
                        it.tel,
                        it.picture_profile,
                        it.picture_iden,
                        it.name,
                        it.surname,
                        it.licence_id,
                        it.birth,
                        it.expdate,
                        it.verify_id
                    ))
                }

                bindingVerify.recyclerView.adapter = ShowVerifyAdapter(User,applicationContext) }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}