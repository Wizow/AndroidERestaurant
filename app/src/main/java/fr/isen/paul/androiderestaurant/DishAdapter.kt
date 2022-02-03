package fr.isen.paul.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.paul.androiderestaurant.databinding.CardViewDesignBinding

class DishAdapter(val dishes: List<String>) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    class DishViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.dish_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return DishViewHolder(view)
    }
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.textView.text = dishes[position]
    }

    override fun getItemCount(): Int = dishes.size
}