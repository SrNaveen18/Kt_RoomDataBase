package com.example.naveen.kt_roomdatabase.roomdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context


@Database(entities = [User::class], version = 1)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDAO

    companion object {
        var INSTANCE: UserRoomDatabase? = null

        fun getAppDataBase(context: Context): UserRoomDatabase? {
            if (INSTANCE == null){
                synchronized(UserRoomDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, UserRoomDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}

