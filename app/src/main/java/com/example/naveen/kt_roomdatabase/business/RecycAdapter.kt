package com.example.naveen.kt_roomdatabase.business

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.naveen.kt_roomdatabase.R
import com.example.naveen.kt_roomdatabase.roomdb.User

class RecycAdapter : RecyclerView.Adapter<RecycViewHolder>() {

    private var mUsers: List<User>? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecycViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.adapter_users, p0, false)
        return RecycViewHolder(view)

    }

    override fun getItemCount(): Int {
        return mUsers?.size ?: 0
    }

    override fun onBindViewHolder(p0: RecycViewHolder, p1: Int) {
        p0.txtUserName.text = mUsers?.get(p1)?.name
    }

    fun setUsers(user: List<User>) {
        this.mUsers = user
        notifyDataSetChanged()
    }

    fun getUserIdAtPosition(position : Int) : User{
      return mUsers!![position]
    }
}