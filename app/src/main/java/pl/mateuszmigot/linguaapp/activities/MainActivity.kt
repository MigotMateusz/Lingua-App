package pl.mateuszmigot.linguaapp.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.mateuszmigot.linguaapp.R
import pl.mateuszmigot.linguaapp.adapters.DeckAdapter
import pl.mateuszmigot.linguaapp.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels<MainViewModel> {
        MainViewModel.DeckListViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val deckAdapter = DeckAdapter(viewModel.deckLiveData.value)
        val recyclerView: RecyclerView = findViewById(R.id.pack)
        recyclerView.adapter = deckAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}