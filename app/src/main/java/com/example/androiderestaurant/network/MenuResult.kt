package com.example.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//va chercher dans le gson le data
class MenuResult(@SerializedName("data") val data : List<Category>) : Serializable {

}