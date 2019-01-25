package com.example.naveen.kt_roomdatabase.roomdb

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "user_table")
class User(val name: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
