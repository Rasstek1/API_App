package com.racine.api_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("images/search")
    fun searchImages(@Query("limit") limit: Int): Call<List<Image>>
}

data class Image(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)
