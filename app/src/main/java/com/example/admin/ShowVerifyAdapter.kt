package com.example.admin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admin.databinding.VerifyCardviewItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowVerifyAdapter (val VerifyList: ArrayList<User>, val context: Context):
    RecyclerView.Adapter<ShowVerifyAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: VerifyCardviewItemBinding) : RecyclerView.ViewHolder(view) {
        init {
            binding.verifyBtn.setOnClickListener {
                val item = VerifyList[adapterPosition]
                val contextView : Context = view.context
                val UserClient = AdminAPI.create()

                val myBuilder = androidx.appcompat.app.AlertDialog.Builder(contextView)
                myBuilder.apply {
                    setTitle("การยืนยันตัวตน")
                    setMessage("ตรวจสอบข้อมูลแล้ว อนุมัติการยืนยันตัวตนของผู้ใช้")
                    setNegativeButton("Yes") { dialog, which ->
                        UserClient.successVerify(item.user_id)
                            .enqueue(object : Callback<User> {
                                override fun onResponse(call: Call<User>, response: Response<User>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "อนุมัติการยืนยันตัวตนของผู้ใช้เรียบร้อยแล้ว", Toast.LENGTH_LONG).show()
                                        contextView.startActivity(Intent(context, verifyActivity::class.java))
                                    }
                                }
                                override fun onFailure(call: Call<User>, t: Throwable) {
                                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                                }
                            })
                    }
                    setPositiveButton ("No"){ dialog, which ->dialog.cancel()}
                    show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowVerifyAdapter.ViewHolder {
        val binding = VerifyCardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ShowVerifyAdapter.ViewHolder, position: Int) {
        val binding_holder = holder.binding

        Glide.with(context).load(VerifyList[position].picture_iden).into(binding_holder.verifyImage)
        binding_holder.verifyName?.text = "ชื่อ: "+VerifyList[position].name
        binding_holder.verifySurname?.text = "นามสกุล: "+VerifyList[position].surname
        binding_holder.verifyLicence?.text = "เลขบัตรประชาชน: "+VerifyList[position].licence_id
        binding_holder.verifyBirthday?.text = "วันเกิด: "+VerifyList[position].birth
        binding_holder.verifyExp?.text = "วันบัตรหมดอายุ: "+VerifyList[position].expdate

    }

    override fun getItemCount(): Int {
        return VerifyList.size
    }
}