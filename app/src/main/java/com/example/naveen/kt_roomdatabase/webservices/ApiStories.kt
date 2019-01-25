package com.example.naveen.kt_roomdatabase.webservices

import android.arch.lifecycle.LiveData
import com.example.naveen.kt_roomdatabase.model.ServerData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiStories {
    @GET("top-headlines?country=in&apiKey=18a90caeb8094908aa8fd1e79308ee71")
    fun getIndianNewsList(@Query("page") page : Int,
                          @Query("pageSize") pageSize : Int): Call<LiveData<ServerData>>
}