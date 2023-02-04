package com.example.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiderestaurant.databinding.ActivityDetailBinding
import com.example.androiderestaurant.databinding.ActivityMenuBinding
import com.example.androiderestaurant.network.Ingredient
import com.example.androiderestaurant.network.MenuResult
import com.example.androiderestaurant.network.Plate
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import layout.CustomAdapter

class DetailActivity : AppCompatActivity() {

    companion object {
        val extraKey = "extraKey"
    }

    lateinit var binding: ActivityDetailBinding
    lateinit var plate : Plate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        plate = intent.getSerializableExtra("plate") as? Plate ?: Plate("", listOf(), listOf(), listOf())

        val ingredient = plate.ingredients.map { it.name }.joinToString(",")
        val imagePlate = binding.imagePlate

        Picasso.get().load(getThumbnail(plate)).into(imagePlate)

        supportActionBar?.title = plate.name

        ShowIngredients(ingredient)
        
    }

    private fun ShowIngredients(ingredient: String) {
        binding.textview.text = ingredient

    }

    private fun getThumbnail(plate : Plate) : String? {
        return if (plate.images.isNotEmpty() && plate.images.firstOrNull()?.isNotEmpty() == true) {
            plate.images.firstOrNull()
        } else {
            null
        }
    }



    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "DetailActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "DetailActivity onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "DetailActivity onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "DetailActivity onStart")
    }

}