package com.tbadhit.submissionbfaa_1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tbadhit.submissionbfaa_1.databinding.ItemCardviewUserBinding
import com.tbadhit.submissionbfaa_1.model.User

class CardViewUserAdapter(private val context: Context) :
    RecyclerView.Adapter<CardViewUserAdapter.CardViewViewHolder>() {

    internal var listUsers = arrayListOf<User>()
    private lateinit var mListener: OnItemClickListener

    // 1
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    // 2
    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    inner class CardViewViewHolder(binding: ItemCardviewUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgPhoto = binding.imgItemPhoto
        val name = binding.tvName
        val username = binding.tvUsername
        val company = binding.tvCompany
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val binding: ItemCardviewUserBinding =
            ItemCardviewUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return CardViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val user = listUsers[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions())
            .into(holder.imgPhoto)

        holder.name.text = user.name
        holder.username.text = user.username
        holder.company.text = user.company

        holder.itemView.setOnClickListener {
            // 3
            mListener.onItemClick(position)
        }

    }

    override fun getItemCount() = listUsers.size
}