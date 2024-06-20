package com.racine.api_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.request.ImageRequest
import com.racine.api_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // URL de l'image à charger (URL directe)
        val imageUrl = "https://www.martinracine.com/evacbook"
        Log.d("MainActivity", "URL de l'image: $imageUrl")

        // Création de la requête d'image avec gestion des erreurs
        val request = ImageRequest.Builder(this)
            .data(imageUrl)
            .target(
                onStart = {
                    // Placeholder ou animation de chargement
                    Log.d("MainActivity", "Chargement de l'image commence")
                    binding.imageView.setImageResource(R.drawable.ic_placeholder)
                },
                onSuccess = { result ->
                    // Image chargée avec succès
                    Log.d("MainActivity", "Image chargée avec succès")
                    binding.imageView.setImageDrawable(result)
                },
                onError = { error ->
                    // Gestion de l'erreur
                    Log.e("MainActivity", "Erreur de chargement d'image : $error")
                    binding.imageView.setImageResource(R.drawable.ic_error)
                }
            )
            .build()

        // Exécute la requête
        binding.imageView.load(request)
    }
}
