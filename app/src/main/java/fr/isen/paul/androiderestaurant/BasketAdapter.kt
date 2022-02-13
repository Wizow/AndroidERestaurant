package fr.isen.paul.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.paul.androiderestaurant.databinding.BasketViewBinding
import fr.isen.paul.androiderestaurant.model.BasketData


class BasketAdapter(private val dishes: MutableList<BasketData>,val onBeenClicked: (BasketData) -> Unit) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    class BasketViewHolder(private val binding: BasketViewBinding): RecyclerView.ViewHolder(binding.root){
        val dishPicture = binding.dishImage
        val dishName = binding.dishName
        val dishQuantity = binding.quantity
        val deleteItem = binding.been
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = BasketViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val dish = dishes[position]
        holder.dishName.text = dish.dishName.name_fr

        Picasso.get()
            .load(dishes[position].dishName.getFirstPicture())//dishes[position].getFirstPicture())
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.dishPicture)

        holder.dishQuantity.text = "Quantité : " +dishes[position].quantity.toString()
        holder.deleteItem.setOnClickListener {
            if(position < dishes.size) {
                val elementToRemove = dishes[position]
                onBeenClicked.invoke(elementToRemove)
                dishes.remove(elementToRemove)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = dishes.size
}