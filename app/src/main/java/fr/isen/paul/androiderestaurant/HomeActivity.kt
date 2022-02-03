package fr.isen.paul.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.paul.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val buttonStarters = binding.buttonStarters
        val buttonDishes = binding.buttonMainDishes
        val buttonDesserts = binding.buttonDesserts


        buttonStarters.setOnClickListener {
            changeActivity(getString(R.string.home_starter))
        }
        buttonDishes.setOnClickListener {
            changeActivity(getString(R.string.home_main_dishes))
        }
        buttonDesserts.setOnClickListener {
            changeActivity(getString(R.string.home_dessert))
        }

    }

    private fun changeActivity(category:String) {
        val changePage: Intent = Intent(this, DishesActivity::class.java)
        changePage.putExtra("category_type",category)
        Log.i("INFO","End of HomeActivity")
        startActivity(changePage)
    }
}