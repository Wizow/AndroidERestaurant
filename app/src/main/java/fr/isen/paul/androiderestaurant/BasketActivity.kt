package fr.isen.paul.androiderestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.paul.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.paul.androiderestaurant.model.BasketData


class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.basketTitle.text = "Votre Panier"
        var dish = intent.getSerializableExtra("data") as List<BasketData>
        Log.e("test", dish.toString())
        displayDishes(dish)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun displayDishes(dishresult : List<BasketData>){
        val recyclerview = binding.basketItem
        recyclerview.layoutManager = LinearLayoutManager(this)

        binding.basketItem.layoutManager = LinearLayoutManager(this)
        binding.basketItem.adapter = BasketAdapter(dishresult)
    }

}