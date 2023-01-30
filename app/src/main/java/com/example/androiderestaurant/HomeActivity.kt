package com.example.androiderestaurant

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root);

        buttonsListener()
    }

    private fun buttonsListener() {
        binding.button.setOnClickListener {
            Log.d( "button", "CLick sur button entrées")
            Toast.makeText(this,"Entrée", Toast.LENGTH_LONG  ).show()
        }

        binding.buttonmain.setOnClickListener {
            Log.d( "button", "CLick sur button desserts")
            Toast.makeText(this,"Desserts", Toast.LENGTH_LONG  ).show()
        }

        binding.button3.setOnClickListener {
            Log.d( "button", "CLick sur button plats")
            Toast.makeText(this,"Plats", Toast.LENGTH_LONG  ).show()
        }
    }
}