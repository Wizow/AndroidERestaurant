package fr.isen.paul.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.paul.androiderestaurant.databinding.CardViewDesignBinding
import fr.isen.paul.androiderestaurant.model.DishModel
import com.squareup.picasso.Picasso

class DishAdapter(private val dishes: List<DishModel>, val onDishClicked : (DishModel) -> Unit) : RecyclerView.Adapter<DishAdapter.DishViewHolder>(){

    class DishViewHolder(val binding: CardViewDesignBinding): RecyclerView.ViewHolder(binding.root){
        val dishPicture = binding.dishPicture
        val dishName = binding.dishName
        val dishPrice = binding.dishPrice
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = CardViewDesignBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        holder.dishName.text = dish.name

        Picasso.get()
            .load(dishes[position].getFirstPicture())
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.dishPicture)

        holder.dishPrice.text = dishes[position].getFormatedPrice()

        holder.itemView.setOnClickListener {
            onDishClicked(dish)
        }
    }

    override fun getItemCount(): Int = dishes.size
}
