package com.example.naveen.kt_roomdatabase

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.naveen.kt_roomdatabase.business.RecycAdapter
import com.example.naveen.kt_roomdatabase.roomdb.User
import com.example.naveen.kt_roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.naveen.kt_roomdatabase.model.Article


class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: RecycAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecycAdapter()
        recyclerView.adapter = adapter


        val helper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    val position = viewHolder.adapterPosition
                    val myUser = adapter.getUserIdAtPosition(position)

                    // Delete the word

                    userViewModel.deleteById(myUser)
                }
            })

        helper.attachToRecyclerView(recyclerView)


        userViewModel = ViewModelProviders.of(this).get(UserViewModel(application)::class.java)

        userViewModel.getAllData().observe(this,
            android.arch.lifecycle.Observer<List<User>> { t ->
                if (t != null) {
                    adapter.setUsers(t)
                }
            })

        userViewModel.getServerData()?.observe(this, android.arch.lifecycle.Observer<List<Article?>> { t ->

            Log.w("TAG_HOME", t?.size.toString())
        })

        btnAddData.setOnClickListener {
            val user = User("Naveen")
            userViewModel.insertData(user)
        }

        btnDeleteAll.setOnClickListener {
            userViewModel.deleteAll()
        }


    }

}
