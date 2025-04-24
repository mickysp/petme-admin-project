package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.databinding.ActivitySelectPostBinding

class selectPost : AppCompatActivity() {
    private lateinit var bindingSelect : ActivitySelectPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSelect = ActivitySelectPostBinding.inflate(layoutInflater)
        setContentView(bindingSelect.root)

        bindingSelect.petAddPost.setOnClickListener {
            val intent = Intent(applicationContext, InsertPet::class.java)
            startActivity(intent)
        }

        bindingSelect.petNoIsadopt.setOnClickListener {
            val intent = Intent(applicationContext, ShowEditDelete::class.java)
            startActivity(intent)
        }

        bindingSelect.petIsadopt.setOnClickListener {
            val intent = Intent(applicationContext, ShowPetIsadopt::class.java)
            startActivity(intent)
        }

    }
}