package fr.isen.paul.androiderestaurant.model

import com.google.gson.annotations.SerializedName

data class RegisterModel(@SerializedName("data") val data: LoginModel, @SerializedName("code") val code: Int)