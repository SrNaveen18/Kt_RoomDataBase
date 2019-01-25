package com.example.naveen.kt_roomdatabase.roomdb

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDAO {

    @Insert
    fun insert(user: User?)

    @Query("SELECT * FROM user_table ORDER BY name ASC")
    fun getAllData() : LiveData<List<User>>

    @Query("DELETE FROM user_table")
    fun deleteAllData()

//    @Query("DELETE FROM user_table")
//    fun deleteDataById()

    @Delete
    fun deleteUser(user: User)
}