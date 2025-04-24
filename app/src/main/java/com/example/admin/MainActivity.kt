package com.example.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            LoginAdmin()
        }
    }

    private fun LoginAdmin() {
        val api: AdminAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AdminAPI::class.java)
        if (binding.loginUsername.text.toString().isEmpty() || binding.loginPassword.text.toString().isEmpty()
        ) {
            Toast.makeText(
                applicationContext, "กรุณากรอกข้อมูลก่อนเข้าสู่ระบบจ้า",
                Toast.LENGTH_SHORT
            ).show()
        }
        api.loginadmin(binding.loginUsername.text.toString(),
            binding.loginPassword.text.toString()
        ).enqueue(object : Callback<Admin> {
            override fun onResponse(call: Call<Admin>, response: Response<Admin>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        applicationContext, "เข้าสู่ระบบสำเร็จแล้วจ้า",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(applicationContext,HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext, "ชื่อหรือรหัสผ่านไม่ถูกต้องนะเตง",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            override fun onFailure(call: Call<Admin>, t: Throwable) {
                Toast.makeText(
                    applicationContext, "Error onFailure" +
                            t.message, Toast.LENGTH_LONG
                ).show()
                println(t.message)
            }
        })
    }
}