package com.example.admin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admin.databinding.NoisadoptCardviewItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditDeleteAdapter(val petList: ArrayList<PetNoIsadotp>, val context: Context):
    RecyclerView.Adapter<EditDeleteAdapter.ViewHolder>() {
    inner class ViewHolder(view: View, val binding: NoisadoptCardviewItemBinding) : RecyclerView.ViewHolder(view) {
        init {
            binding.edtBtn.setOnClickListener {
                val item = petList[adapterPosition]

                val contextView : Context = view.context
                val intent = Intent(context, UpdateDelete::class.java)
                intent.putExtra("mId",item.petdetail_id.toString())
                intent.putExtra("mDeleteStatus",item.delete_status.toString())
                intent.putExtra("mOld",item.old.toString())
                intent.putExtra("mStaPet",item.status_pet)
                intent.putExtra("mMonth",item.month.toString())
                intent.putExtra("mDistrict",item.district)
                intent.putExtra("mAdoptid",item.isadopt.toString())
                intent.putExtra("mAdminid",item.admin_id.toString())
                intent.putExtra("mDetail",item.detail)
                intent.putExtra("mName",item.name)
                intent.putExtra("mType",item.typeanimal)
                intent.putExtra("mGender",item.gender)
                intent.putExtra("mImage",item.picture_pet)
                contextView.startActivity(intent)
            }

            binding.deleteBtn.setOnClickListener {
                val item = petList[adapterPosition]
                val contextView : Context = view.context
                val PetClient = PetAPI.create()

                val myBuilder = androidx.appcompat.app.AlertDialog.Builder(contextView)
                myBuilder.apply {
                    setTitle("Warning")
                    setMessage("Do you want to soft delete the pet?")
                    setNegativeButton("Yes") { dialog, which ->
                        PetClient.softDeletePet(item.petdetail_id)
                            .enqueue(object : Callback<Pet> {
                                override fun onResponse(call: Call<Pet>, response: Response<Pet>) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "Successfully Soft Deleted", Toast.LENGTH_LONG).show()
                                        contextView.startActivity(Intent(context, ShowEditDelete::class.java))
                                    }
                                }
                                override fun onFailure(call: Call<Pet>, t: Throwable) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditDeleteAdapter.ViewHolder {
        val binding = NoisadoptCardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: EditDeleteAdapter.ViewHolder, position: Int) {
        val binding_holder = holder.binding

        binding_holder.txtName?.text = petList[position].name
        binding_holder.txtDistrict?.text = petList[position].district
        binding_holder.txtGender?.text = "เพศ: " + petList[position].gender
        binding_holder.txtOld?.text = "อายุ: " + petList[position].old.toString() + " ปี " + petList[position].month.toString() + " เดือน "
        binding_holder.txtStatus?.text = "สถานะ: " + petList[position].status_pet
        binding_holder.txtType?.text = "ประเภท: " + petList[position].typeanimal
        binding_holder.txtDetail?.text = "รายละเอียดเพิ่มเติม: " + petList[position].detail
        Glide.with(context).load(petList[position].picture_pet).into(binding_holder.imagePet)
    }

    override fun getItemCount(): Int {
        return petList.size
}
    }