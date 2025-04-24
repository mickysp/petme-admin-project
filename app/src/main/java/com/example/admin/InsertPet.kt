package com.example.admin

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.admin.databinding.ActivityInsertPetBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class InsertPet : AppCompatActivity() {
    private lateinit var bindingInsert: ActivityInsertPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInsert = ActivityInsertPetBinding.inflate(layoutInflater)
        setContentView(bindingInsert.root)
    }

    fun addPet(v:View) {
        val contextView : Context = v.context
        val api: PetAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PetAPI::class.java)
        api.insertPet(
            bindingInsert.postName.text.toString(),
            bindingInsert.postGender.text.toString(),
            bindingInsert.postOld.text.toString().toInt(),
            bindingInsert.postMonth.text.toString().toInt(),
            bindingInsert.postStatus.text.toString(),
            bindingInsert.postType.text.toString(),
            bindingInsert.postDistrict.text.toString(),
            bindingInsert.postDetail.text.toString(),
            bindingInsert.postImage.text.toString()
        )
            .enqueue(object : Callback<Pet> {
            override fun onResponse(
                call: Call<Pet>,
                response: retrofit2.Response<Pet>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        applicationContext, "Successfully Inserted",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                    contextView.startActivity(Intent(applicationContext, selectPost::class.java))
                } else {
                    Toast.makeText(
                        applicationContext, "Inserted Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Pet>, t: Throwable) {
                Toast.makeText(
                    applicationContext, "Error onFailure " +
                            t.message, Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}

