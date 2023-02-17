package com.example.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiderestaurant.databinding.ActivityDetailBinding
import com.example.androiderestaurant.databinding.ActivityValidationBinding
import com.example.androiderestaurant.network.Plate

class ValidationActivity : AppCompatActivity() {
    companion object {
        val extraKey = "extrakey"
    }

    lateinit var binding: ActivityValidationBinding
    lateinit var plate : Plate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)

        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Valider le panier"

        plate = intent.getSerializableExtra("plate") as? Plate ?: Plate("", listOf(), listOf(), listOf())



      //  binding.commande.text = plate.name.map{it.name}.joinToString(",")

    }
}