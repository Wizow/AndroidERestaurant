package fr.isen.paul.androiderestaurant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.paul.androiderestaurant.databinding.BasketViewBinding
import fr.isen.paul.androiderestaurant.model.BasketData


class BasketAdapter(private val dishes: List<BasketData>) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    class BasketViewHolder(private val binding: BasketViewBinding): RecyclerView.ViewHolder(binding.root){
        val dishPicture = binding.dishPicture
        val dishName = binding.dishName
        val dishQuantity = binding.quantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = BasketViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val dish = dishes[position]
        holder.dishName.text = dish.DishName

        Picasso.get()
            .load(R.drawable.ic_launcher_foreground)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.dishPicture)

        holder.dishQuantity.text = "Quantité : " +dishes[position].quantity.toString()
        val data = dishes[position]
    }

    override fun getItemCount(): Int = dishes.size
}