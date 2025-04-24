package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var bindingHome: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHome = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindingHome.root)

        bindingHome.btnSeePost.setOnClickListener {
            val intent = Intent(applicationContext,selectPost::class.java)
            startActivity(intent)
        }

        bindingHome.btnSeeDonate.setOnClickListener {
            val intent = Intent(applicationContext,selectDonateCheck::class.java)
            startActivity(intent)
        }
        bindingHome.btnSeeVerify.setOnClickListener {
            val intent = Intent(applicationContext, verifyActivity::class.java)
            startActivity(intent)
        }

    }

}