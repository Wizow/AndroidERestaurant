package fr.isen.paul.androiderestaurant.model

import java.io.Serializable

data class AccountModel(val familyName:String, val firstName: String,val address: String, val mailAddress: String, val password: String): Serializable