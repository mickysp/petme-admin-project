package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.admin.databinding.ActivityUpdateDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDelete : AppCompatActivity() {
    private lateinit var bindingEdit : ActivityUpdateDeleteBinding
    val PetClient = PetAPI.create()
    var uId : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingEdit = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        setContentView(bindingEdit.root )
        uId = intent.getStringExtra("mId").toString()
        val uName = intent.getStringExtra("mName")
        val uGender = intent.getStringExtra("mGender")
        val uOld = intent.getStringExtra("mOld").toString()
        val uMonth = intent.getStringExtra("mMonth").toString()
        val uType = intent.getStringExtra("mType")
        val uStatus = intent.getStringExtra("mStatus")
        val uDistrict = intent.getStringExtra("mDistrict")
        val uDetail = intent.getStringExtra("mDetail")
        val uImage = intent.getStringExtra("mImage")

        bindingEdit.updateID.setText(uId)
        bindingEdit.updateID.isEnabled = false
        bindingEdit.updateName.setText(uName)
        bindingEdit.updateGender.setText(uGender)
        bindingEdit.updateOld.setText(uOld)
        bindingEdit.updateMonth.setText(uMonth)
        bindingEdit.updateType.setText(uType)
        bindingEdit.updateStatus.setText(uStatus)
        bindingEdit.updateDistrict.setText(uDistrict)
        bindingEdit.updateDetail.setText(uDetail)
        bindingEdit.updateImage.setText(uImage)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== android.R.id.home)
        { finish() }
        return super.onOptionsItemSelected(item)
    }
    fun updatePet(v: View){
        PetClient.updatePet (
            uId.toInt(),
            bindingEdit.updateName.text.toString(),
            bindingEdit.updateGender.text.toString(),
            bindingEdit.updateOld.text.toString().toInt(),
            bindingEdit.updateMonth.text.toString().toInt(),
            bindingEdit.updateType.text.toString(),
            bindingEdit.updateStatus.text.toString(),
            bindingEdit.updateDistrict.text.toString(),
            bindingEdit.updateDetail.text.toString(),
            bindingEdit.updateImage.text.toString()).enqueue(object : Callback<Pet> {
            override fun onResponse(call: Call<Pet>, response: Response<Pet>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Updated", Toast.LENGTH_SHORT).show()
                    finish()
                }else{ Toast.makeText(applicationContext,"Error ", Toast.LENGTH_SHORT).show() }
            }
            override fun onFailure(call: Call<Pet>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show() }
        })
    }
}