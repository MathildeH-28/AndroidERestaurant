package com.example.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiderestaurant.databinding.ActivityHomeBinding
import com.example.androiderestaurant.databinding.ActivityMenuBinding
import layout.CustomAdapter

enum class Category { STARTER, MAIN, DESSERT}

class MenuActivity : AppCompatActivity() {

    companion object {
        val extraKey = "extraKey"
    }

    lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getSerializableExtra(extraKey) as? Category

        supportActionBar?.title = categoryName(category?: Category.STARTER)
        //if category == nul {category = STARTER}

        showDatas()


    }

    private fun showDatas() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CustomAdapter(listOf("1", "2", "3")) { position ->
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "MenuActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "MenuActivity onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "MenuActivity onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "MenuActivity onStart")
    }


    private fun categoryName(category: Category) : String {
        return when(category) {
            Category.STARTER -> getString(R.string.starter)
            Category.MAIN -> getString(R.string.main)
            Category.DESSERT -> getString(R.string.finish)
        }
    }
}



