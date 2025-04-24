package com.example.admin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admin.databinding.ActivityShowPetIsadoptBinding
import com.example.admin.databinding.IsadoptCardviewItemBinding
import com.example.admin.databinding.NoisadoptCardviewItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowPetIsadoptAdapter (val petList: ArrayList<Pet>, val context: Context):
    RecyclerView.Adapter<ShowPetIsadoptAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: IsadoptCardviewItemBinding) : RecyclerView.ViewHolder(view) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowPetIsadoptAdapter.ViewHolder {
        val binding = IsadoptCardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ShowPetIsadoptAdapter.ViewHolder, position: Int) {
        val binding_holder = holder.binding

        binding_holder.txtName?.text = petList[position].name
        binding_holder.txtOwner?.text = "เจ้าของ: " + petList[position].username
        binding_holder.txtDistrict?.text = petList[position].district
        binding_holder.txtAddress?.text = petList[position].address
        Glide.with(context).load(petList[position].picture_pet).into(binding_holder.imagePet)
    }

    override fun getItemCount(): Int {
        return petList.size
    }
}