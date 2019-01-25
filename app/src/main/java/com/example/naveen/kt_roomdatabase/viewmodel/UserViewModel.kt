package com.example.naveen.kt_roomdatabase.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.naveen.kt_roomdatabase.model.Article
import com.example.naveen.kt_roomdatabase.repositary.UserRepositary
import com.example.naveen.kt_roomdatabase.roomdb.User

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepositary: UserRepositary = UserRepositary(application)
    private var allUserData: LiveData<List<User>>
    private var serverData : LiveData<List<Article?>>?

    init {
        allUserData = userRepositary.getAllData()
        serverData = userRepositary.getDataFromServer()
    }

    fun getAllData(): LiveData<List<User>> {
        return allUserData
    }

    fun insertData(user: User) {
        userRepositary.insertData(user)
    }

    fun deleteAll() {
        userRepositary.deleteAll()
    }

    fun deleteById(user: User) {
        userRepositary.deleteUser(user)
    }

    fun getServerData() :  LiveData<List<Article?>>?{
        return serverData
    }
}