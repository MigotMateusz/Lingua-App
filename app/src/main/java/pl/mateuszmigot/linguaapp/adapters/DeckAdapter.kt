package pl.mateuszmigot.linguaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.mateuszmigot.linguaapp.R
import pl.mateuszmigot.linguaapp.models.Deck

class DeckAdapter(private val dataSet: List<Deck>?) : RecyclerView.Adapter<DeckAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.deck_recyclerview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet!![position].name;
    }
    override fun getItemCount() = dataSet!!.size
}