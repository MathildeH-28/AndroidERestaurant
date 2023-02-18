package layout

import android.provider.DocumentsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.androiderestaurant.databinding.CellCustomBinding
import com.example.androiderestaurant.network.Plate
import com.squareup.picasso.Picasso

class CustomAdapter(val items : List<Plate>, val clickListener: (Plate) -> Unit) : RecyclerView.Adapter<CustomAdapter.CellViewHolder>() {
    class CellViewHolder(binding: CellCustomBinding) : RecyclerView.ViewHolder(binding.root) {
        val textView : TextView = binding.itemName
        val imageView = binding.imageView
        val priceTextView = binding.priceTextView
        val root: ConstraintLayout = binding.root
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CellViewHolder {
        val binding = CellCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val plate = items[position]
        holder.textView.text = items[position].name
        holder.priceTextView.text = items[position].prices.first().prices.toString() + "€"
        Picasso.get().load(getThumbnail(plate)).into(holder.imageView)
        holder.root.setOnClickListener {
            Log.d("click", "click on ${position}")
            clickListener(items[position])
        }
    }

    private fun getThumbnail(plate: Plate): String? {
        return if (plate.images.isNotEmpty() && plate.images.firstOrNull()?.isNotEmpty() == true) {
            plate.images.firstOrNull()
    } else {
        null
    }
    }
    override fun getItemCount(): Int {
        return items.count()
    }

}