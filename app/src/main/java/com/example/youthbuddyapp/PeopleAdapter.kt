package com.example.youthbuddyapp

import android.content.Context
import android.provider.Contacts.People
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youthbuddyapp.databinding.UserRecyclerProfileBinding
import com.squareup.picasso.Picasso

class PeopleAdapter(private val context: Context, private var peopleList: ArrayList<com.example.youthbuddyapp.models.People>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list:ArrayList<com.example.youthbuddyapp.models.People> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = UserRecyclerProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserCardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val person = list[position]
        Log.d("testamm","$person")
        holder as UserCardViewHolder
        Picasso.get().load(peopleList[position].photoUrl).into(holder.photo)
        holder.name.text = person.name
        holder.email.text = person.email
    }

    fun addPeople(people: List<com.example.youthbuddyapp.models.People>){
        list.clear()
        list.addAll(people)
        notifyDataSetChanged()
    }

    class UserCardViewHolder(val binding: UserRecyclerProfileBinding):ViewHolder(binding.root){
        val photo = binding.userImage
        val name = binding.authorName
        val email = binding.emailAddress
    }
}