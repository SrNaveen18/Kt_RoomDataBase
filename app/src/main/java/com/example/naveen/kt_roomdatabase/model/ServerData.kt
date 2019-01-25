package com.example.naveen.kt_roomdatabase.model

import android.arch.lifecycle.LiveData


data class ServerData(
    val status: String,
    val totalResults: Int,
    val articles: LiveData<List<Article?>>
)

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(
    val id: String,
    val name: String
)