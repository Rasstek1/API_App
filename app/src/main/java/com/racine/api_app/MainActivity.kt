package com.racine.api_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.racine.api_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Version Coil : Charger une image directement depuis une URL
        // URL directe de l'image
        val imageUrl = "https://static.wixstatic.com/media/4554bd_81e4e2947e0f4b8a8f8f1b084ee2e40e~mv2.jpg/v1/fill/w_1400,h_1866,al_c,q_90,usm_0.66_1.00_0.01,enc_auto/EVA_8(4X3).jpg"

        // Charger l'image avec Coil
        binding.imageView.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_error)  // Image d'erreur en cas de problème
        }

        // --- Version Retrofit ---
        // Décommenter ce bloc pour utiliser Retrofit et commenter le bloc Coil ci-dessus

        /*
        // Initialiser Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        fetchImages(apiService)
        */
    }

    // Fonction pour récupérer des images depuis l'API
    private fun fetchImages(apiService: ApiService) {
        apiService.searchImages(10).enqueue(object : Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                if (response.isSuccessful) {
                    val images = response.body()
                    images?.let {
                        // Charger l'image avec Coil en utilisant l'URL de la réponse
                        binding.imageView.load(it[0].url) {
                            crossfade(true)
                            placeholder(R.drawable.ic_placeholder)
                            error(R.drawable.ic_error)  // Image d'erreur en cas de problème
                        }
                    }
                } else {
                    Log.e("MainActivity", "Erreur de réponse de l'API")
                }
            }

            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                Log.e("MainActivity", "Échec de la requête API", t)
            }
        })
    }
}
