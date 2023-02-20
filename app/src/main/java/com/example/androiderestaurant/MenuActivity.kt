package com.example.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.androiderestaurant.databinding.ActivityHomeBinding
import com.example.androiderestaurant.databinding.ActivityMenuBinding
import com.example.androiderestaurant.network.MenuResult
import com.example.androiderestaurant.network.NetworkConstants
import com.example.androiderestaurant.network.Plate
import com.google.gson.GsonBuilder
import layout.CustomAdapter
import org.json.JSONObject
import java.lang.reflect.Method

enum class Category { STARTER, MAIN, DESSERT}

class MenuActivity : AppCompatActivity() {

    companion object {
        val extraKey = "extraKey"
    }

    lateinit var binding: ActivityMenuBinding
    lateinit var currentCategory: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getSerializableExtra(extraKey) as? Category
        currentCategory = category ?: Category.STARTER



        supportActionBar?.title = categoryName()
        //if category == nul {category = STARTER}

        makeRequest()

    }

    private fun makeRequest() {
        val queue = Volley.newRequestQueue(this)
        val params = JSONObject()
        params.put(NetworkConstants.idShopKey, 1)
        val request = JsonObjectRequest(
            com.android.volley.Request.Method.POST,
            NetworkConstants.url,
            params,
            {result ->
                //Success of request
                Log.d("resquest", result.toString(2))
                parseData(result.toString())

            },
            {error ->
                //Error when request
                Log.e("resquest", error.toString())
            }
        )
        queue.add(request)
        //showDatas()
    }

    private fun parseData(data : String) {
        val result = GsonBuilder().create().fromJson(data, MenuResult::class.java)
        val category = result.data.first { it.name == categoryFilterKey() }
        showDatas(category)
       // Log.d("request", "parsing")
    }
    private fun showDatas(category: com.example.androiderestaurant.network.Category) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CustomAdapter(category.items) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("plate", it)
         //   intent.putExtra("category", category.name)
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


    private fun categoryName() : String {
        return when(currentCategory) {
            Category.STARTER -> getString(R.string.starter)
            Category.MAIN -> getString(R.string.main)
            Category.DESSERT -> getString(R.string.finish)
        }
    }
    private fun categoryFilterKey() : String {
        return when(currentCategory) {
            Category.STARTER -> "Entrées"
            Category.MAIN -> "Plats"
            Category.DESSERT -> "Desserts"
        }
    }
}



