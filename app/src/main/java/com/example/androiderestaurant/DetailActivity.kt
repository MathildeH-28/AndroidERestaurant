package com.example.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Contacts.Photo
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiderestaurant.databinding.ActivityDetailBinding
import com.example.androiderestaurant.databinding.ActivityMenuBinding
import com.example.androiderestaurant.network.Ingredient
import com.example.androiderestaurant.network.MenuResult
import com.example.androiderestaurant.network.Plate
import com.example.androiderestaurant.network.Price
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import layout.CustomAdapter

class DetailActivity : AppCompatActivity() {

    companion object {
        val extraKey = "extraKey"
    }

    lateinit var binding: ActivityDetailBinding
    lateinit var plate : Plate
  //  lateinit var category: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        plate = intent.getSerializableExtra("plate") as? Plate ?: Plate("", listOf(), listOf(), listOf())

        val ingredient = plate.ingredients.map { it.name }.joinToString(",")
        val imagePlate = binding.imagePlate
        val price = plate.prices.map { it.prices }.toString()
        val priceF : Float  = plate.prices.map {it.prices}.component1()
       // val category = category.name




        Picasso.get().load(getThumbnail(plate)).into(imagePlate)

        supportActionBar?.title = plate.name

        ShowIngredients(ingredient)
        buttonsListener(priceF)
        totalPrice(price)

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
    var nbrplat = 0
    var price2 : Float = 0F

    private fun totalPrice(price : String) {
        binding.buttonOK.text = "${price}"
    }

    private fun ajoutPanier(priceF : Float) {
        nbrplat = nbrplat + 1
        var price2 : Float = priceF * nbrplat
        Log.e("button ajout", "${price2}")
        binding.nbrPlats.text = "${nbrplat}"
        binding.buttonOK.text = "Total ${price2} €"



    }
        //le but serait qu'à chaque fois qu'on appuie sur le bouton plus le nombre s'incrémente, comme un compteur

    private fun retirePanier(priceF: Float) {
        nbrplat = nbrplat - 1
        var price2 = priceF * nbrplat
        Log.e("button ajout", "${nbrplat}")
        binding.nbrPlats.text = "${nbrplat}"
        binding.buttonOK.text = "Total ${price2} €"
    }
    private fun buttonsListener(priceF: Float/*, category : String*/) {
        binding.buttonAjt.setOnClickListener {
            Log.d("button", "CLick sur button ajouté")
            ajoutPanier(priceF)
        }
        binding.buttonRet.setOnClickListener {
            Log.d("button", "CLick sur button retiré")
            retirePanier(priceF)

        }
        binding.buttonOK.setOnClickListener {
            Log.d("button", "CLick sur button retiré")
            binding.panier.text = "${nbrplat}"
        }

        binding.imagePanier.setOnClickListener {
            val intent = Intent(this, ValidationActivity::class.java)
            intent.putExtra("priceF", priceF)
            intent.putExtra("nbrplat", nbrplat)
            intent.putExtra("plat", plate.name)
          //  intent.putExtra("category", category)
            startActivity(intent)
        }

        binding.butonHome2.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("priceF", priceF)
            intent.putExtra("nbrplat", nbrplat)
            intent.putExtra("plat", plate.name)
          //  intent.putExtra("category", category) ça nous permettrait de savoir quelle category de plat c'est pour afficher 3 catégories différentes sur la page validation
            startActivity(intent)
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