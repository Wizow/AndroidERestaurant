package fr.isen.paul.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.paul.androiderestaurant.databinding.ActivityDetail2Binding
import fr.isen.paul.androiderestaurant.model.BasketData
import fr.isen.paul.androiderestaurant.model.DishBasket
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
        val listIngredients =
            (intent.getSerializableExtra("dish") as DishModel).getFormatedIngredients()
        for (i in listIngredients.indices) {
            list += listIngredients[i].name_fr + ", "
        }
        binding.detail.text = list
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        var dishName = (intent.getSerializableExtra("dish") as DishModel)
        var counter=1
        var price = (intent.getSerializableExtra("dish") as DishModel).prices[0].price
        val data = ArrayList<BasketData>()
        var totalPrice = price.toFloat()*counter
        val buttonMoins = binding.buttonMoins
        val buttonPlus = binding.buttonPlus
        val buttonTotal = binding.buttonTotal
        val buttonPanier = binding.buttonPanier
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

        buttonTotal.setOnClickListener{
            val filename = "/panier.json"
            Snackbar.make(it,"Ajouté au panier", Snackbar.LENGTH_LONG).show()
            File(cacheDir.absolutePath + filename).bufferedWriter().use { file->
                file.write(Gson().toJson(DishBasket(dishName,counter)))
            }

            val recup = File(cacheDir.absolutePath + filename).bufferedReader().readText();

            val resultat = Gson().fromJson(recup,DishBasket::class.java);
            Log.d("panier", recup)
            var bool = false
            for(i in data.indices)
                if(resultat.dishName.name_fr==data[i].DishName){
                    data[i].quantity += resultat.quantity
                    bool = true
                }
                else {
                    data.add(BasketData(resultat.dishName.name_fr,resultat.quantity))
                    bool = true}
            if(!bool) data.add(BasketData(resultat.dishName.name_fr,resultat.quantity))
            Log.e("test", resultat.toString())
        }
        buttonPanier.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }
        return true
    }
    private fun initDetail(dish: DishModel){
        binding.detailTitle.text = dish.name_fr
        binding.PhotoPager.adapter = PictureAdapter(this,dish.pictures)
    }

}