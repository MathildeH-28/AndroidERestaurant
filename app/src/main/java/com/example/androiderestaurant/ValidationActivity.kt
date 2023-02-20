package com.example.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiderestaurant.databinding.ActivityDetailBinding
import com.example.androiderestaurant.databinding.ActivityValidationBinding
import com.example.androiderestaurant.network.Plate
import java.time.Instant

class ValidationActivity : AppCompatActivity() {
    companion object {
        val extraKey = "extrakey"
    }

    lateinit var binding: ActivityValidationBinding
    lateinit var plate : Plate
   // lateinit var category: Category


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)

        var nbrplat : Int = intent.getIntExtra("nbrplat", 0)
        var priceF : Float = intent.getFloatExtra("priceF", 0F)
        var priceT : Float = nbrplat * priceF
        var plat = intent.getStringExtra("plat")


        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Valider le panier"

        plate = intent.getSerializableExtra("plate") as? Plate ?: Plate("", listOf(), listOf(), listOf())


        buttonsListener(nbrplat, priceF, priceT, plat)

      //  if(category.name == "Entrée" ) {
            binding.commandeE.text = "${nbrplat} " + plat + " ${priceT} €"
     //   }
    //    if(category.name == "Plats" ) {
            binding.commandeP.text = "${nbrplat} " + plat + " ${priceT} €"
   //     }
   //     if(category.name == "Desserts" ) {
            binding.commandeD.text = "${nbrplat} " + plat + " ${priceT} €"
    //    }
    }

    private fun buttonsListener(nbrplat : Int, priceF : Float, priceT : Float, plat : String?) {
        binding.butonHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("priceT", priceT)
            intent.putExtra("nbrplat", nbrplat)
            intent.putExtra("plat", plat)
            intent.putExtra("priceF", priceF)
           // intent.putExtra("category", category.name)
            startActivity(intent)
        }
    }
}