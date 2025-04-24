package com.example.admin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.databinding.ShowDonateMoreItemBinding

class ShowDonateMoreAdapter (val donateList: ArrayList<Donate>, val context: Context):
    RecyclerView.Adapter<ShowDonateMoreAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: ShowDonateMoreItemBinding) : RecyclerView.ViewHolder(view) {
        init {
            binding.checkDonateBtn.setOnClickListener {
                val item = donateList[adapterPosition]

                val contextView : Context = view.context
                val intent = Intent(context, ShowInformationDonate::class.java)
                intent.putExtra("doId",item.donate_id.toString())
                intent.putExtra("doUserId",item.user_id.toString())
                intent.putExtra("doImage",item.picture_pay)
                intent.putExtra("doMoney",item.balance.toString())
                intent.putExtra("doNumber",item.last4digits)
                intent.putExtra("doUsername",item.username)
                intent.putExtra("doAddress",item.address)
                intent.putExtra("doTel",item.tel)


                contextView.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowDonateMoreAdapter.ViewHolder {
        val binding = ShowDonateMoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ShowDonateMoreAdapter.ViewHolder, position: Int) {
        val binding_holder = holder.binding

        binding_holder.txtMoney?.text = "+ " + donateList[position].balance.toString()
        binding_holder.txtName?.text = donateList[position].username
        binding_holder.txtNum?.text = donateList[position].last4digits


    }

    override fun getItemCount(): Int {
        return donateList.size
    }
}