package com.example.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Price (
    @SerializedName("price") val prices: Float,
  //  @SerializedName("price") val priceF: Float,
): Serializable