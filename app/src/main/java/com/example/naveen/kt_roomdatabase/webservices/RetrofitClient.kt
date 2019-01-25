package com.example.naveen.kt_roomdatabase.webservices

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        val BASE_URL : String = "https://newsapi.org/v2/"
        fun getRetrotit() : Retrofit {
            val gson : Gson = GsonBuilder().setLenient().create()
            val retrofit : Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson)).
                    baseUrl(BASE_URL)
                .build()
            return retrofit
        }
    }
}