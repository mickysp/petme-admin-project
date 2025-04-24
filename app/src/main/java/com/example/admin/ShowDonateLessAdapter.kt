package com.example.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admin.databinding.IsadoptCardviewItemBinding
import com.example.admin.databinding.ShowDonateLessItemBinding

class ShowDonateLessAdapter (val donateList: ArrayList<Donate>, val context: Context):
    RecyclerView.Adapter<ShowDonateLessAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: ShowDonateLessItemBinding) : RecyclerView.ViewHolder(view) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowDonateLessAdapter.ViewHolder {
        val binding = ShowDonateLessItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ShowDonateLessAdapter.ViewHolder, position: Int) {
        val binding_holder = holder.binding

        binding_holder.txtMoney?.text = "+ " + donateList[position].balance.toString()
        binding_holder.txtName?.text = donateList[position].username
        binding_holder.txtNum?.text = donateList[position].last4digits
    }

    override fun getItemCount(): Int {
        return donateList.size
    }
}