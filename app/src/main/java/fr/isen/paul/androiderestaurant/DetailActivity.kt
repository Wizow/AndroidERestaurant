package fr.isen.paul.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.paul.androiderestaurant.databinding.ActivityDetail2Binding
import fr.isen.paul.androiderestaurant.model.DishModel
import java.io.File


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetail2Binding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var dish = intent.getSerializableExtra("dish") as DishModel
        initDetail(dish)
        var list = ""
        val listIngredients = (intent.getSerializableExtra("dish") as DishModel).getFormatedIngredients()
        for(i in listIngredients.indices){
            list += listIngredients[i].name_fr + ", "
        }
        binding.detail.text = list
        var counter=1
        var price = (intent.getSerializableExtra("dish") as DishModel).prices[0].price
        var totalPrice = price.toFloat()*counter
        val buttonMoins = binding.buttonMoins
        val buttonPlus = binding.buttonPlus
        binding.buttonTotal.text = "Total : " + price+"€"
        buttonPlus.setOnClickListener {
            counter++
            totalPrice = price.toFloat()*counter
            binding.counter.text = counter.toString()
            binding.buttonTotal.text = "Total : " + totalPrice.toString() + "€"
        }
        buttonMoins.setOnClickListener {
            if (counter!=1){
                counter--
                totalPrice = price.toFloat()*counter
                binding.counter.text = counter.toString()
                binding.buttonTotal.text = "Total : " + totalPrice.toString() + "€"
            }
        }

        val buttonTotal = binding.buttonTotal
        buttonTotal.setOnClickListener{
            File(this.filesDir, "panier.txt").outputStream().use {
                it.write("Bonjour !!!".toByteArray())
            }
        }

    }
    private fun initDetail(dish: DishModel){
        binding.detailTitle.text = dish.name_fr
        //binding.dishPhotoPager.adapter = PictureAdapter(this,dish.pictures)
    }

}