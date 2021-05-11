package pl.mateuszmigot.linguaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import pl.mateuszmigot.linguaapp.R
import pl.mateuszmigot.linguaapp.models.Deck

class DeckAdapter(private val dataSet: List<Deck>?) : RecyclerView.Adapter<DeckAdapter.ViewHolder>() {
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val practiseButton: MaterialButton = view.findViewById(R.id.practiseButton)
        val editButton: MaterialButton = view.findViewById(R.id.editButton)
        val precentTextView: MaterialTextView = view.findViewById(R.id.materialTextView)

        init {
            practiseButton.setOnClickListener { clickPractise() }
            editButton.setOnClickListener { clickEdit() }
        }
        fun clickPractise() {
            Toast.makeText(view.context, "Clicked practise", Toast.LENGTH_SHORT).show()
        }
        fun clickEdit() {
            Toast.makeText(view.context, "Clicked Edit", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.deck_recyclerview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet!![position].name
        holder.precentTextView.text = "25%"
    }
    override fun getItemCount() = dataSet!!.size
}