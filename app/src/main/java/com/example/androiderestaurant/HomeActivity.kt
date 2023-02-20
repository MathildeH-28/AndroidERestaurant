package com.example.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.androiderestaurant.databinding.ActivityHomeBinding
import com.example.androiderestaurant.network.Plate

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var nbrplat : Int = intent.getIntExtra("nbrplat", 0)
        var priceF : Float = intent.getFloatExtra("priceF", 0F)
        var priceT : Float = intent.getFloatExtra("priceT", 0F)
        var plat = intent.getStringExtra("plat")
        var category = intent.getStringExtra("category")

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root);

        buttonsListener(nbrplat, priceF, priceT, plat, category)
    }

    override fun onStart() {
        super.onStart()
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun showCategory(category: Category) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra(MenuActivity.extraKey, category)
        startActivity(intent)

    }

    private fun showPlate(plats: Plate) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra(MenuActivity.extraKey, plats)
        startActivity(intent)

    }

    private fun buttonsListener(nbrplat : Int, priceF : Float, priceT : Float, plat : String?, category : String?) {
        binding.button.setOnClickListener {
            Log.d( "button", "CLick sur button entrées")
            Toast.makeText(this,"Entrées", Toast.LENGTH_LONG  ).show()
            showCategory(Category.STARTER )
        }

        binding.buttonmain.setOnClickListener {
            Log.d( "button", "CLick sur button desserts")
            Toast.makeText(this,"Desserts", Toast.LENGTH_LONG  ).show()
            showCategory(Category.DESSERT)
        }

        binding.button3.setOnClickListener {
            Log.d( "button", "CLick sur button plats")
            Toast.makeText(this,"Plats", Toast.LENGTH_LONG  ).show()
            showCategory(Category.MAIN)

        }

        binding.validPanier.setOnClickListener{
            Toast.makeText(this,"Panier", Toast.LENGTH_LONG  ).show()
            val intent = Intent(this, ValidationActivity::class.java)
            intent.putExtra("Validation","panier")
            intent.putExtra("priceT", priceT)
            intent.putExtra("nbrplat", nbrplat)
            intent.putExtra("plat", plat)
            intent.putExtra("priceF", priceF)
            intent.putExtra("category", category)
            startActivity(intent)
        }
    }
}