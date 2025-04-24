package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.admin.databinding.ActivityShowInformationDonateBinding
import com.example.admin.databinding.ActivityUpdateDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowInformationDonate : AppCompatActivity() {
    private lateinit var bindingDonate: ActivityShowInformationDonateBinding
    val DonateClient = DonateAPI.create()
    var uId : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDonate = ActivityShowInformationDonateBinding.inflate(layoutInflater)
        setContentView(bindingDonate.root )

        uId = intent.getStringExtra("doId").toString()
        val uUserId = intent.getStringExtra("doUserId").toString()
        val uImage = intent.getStringExtra("doImage")
        val uMoney = intent.getStringExtra("doMoney").toString()
        val uNumber = intent.getStringExtra("doNumber")
        val uUsername = intent.getStringExtra("doUsername")
        val uAddress = intent.getStringExtra("doAddress")
        val uTel = intent.getStringExtra("doTel")


        bindingDonate.Money.text = "ยอดบริจาค: ${uMoney} บาท"
        bindingDonate.Name.text = "ชื่อ-นามสกุล: ${uUsername}"
        bindingDonate.Number.text = "เลขบัญชีสี่ตัวสุดท้าย: ${uNumber}"
        Glide.with(applicationContext).load(uImage).into(bindingDonate.image)
        bindingDonate.Address.text = "ที่อยู่: ${uAddress}"
        bindingDonate.Tel.text = "เบอร์โทรศัพท์: ${uTel}"


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== android.R.id.home)
        { finish() }
        return super.onOptionsItemSelected(item)
    }

}