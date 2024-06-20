package com.racine.api_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Déclaration de la classe de données Image pour la réponse de l'API
data class Image(val url: String)

// Déclaration de l'interface ApiService pour Retrofit
interface ApiService {
    @GET("images/search")
    fun searchImages(@Query("limit") limit: Int): Call<List<Image>>
}
