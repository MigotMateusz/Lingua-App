package pl.mateuszmigot.linguaapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pl.mateuszmigot.linguaapp.R
import pl.mateuszmigot.linguaapp.adapters.DeckAdapter
import pl.mateuszmigot.linguaapp.data.AppDatabase
import pl.mateuszmigot.linguaapp.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.DeckListViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarMainActivity);
        setSupportActionBar(toolbar);
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.addDeckFloatingButton)
        floatingActionButton.setOnClickListener { click() }
        val deckAdapter = DeckAdapter(viewModel.deckLiveData.value)
        val recyclerView: RecyclerView = findViewById(R.id.pack)
        recyclerView.adapter = deckAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun click() {
        Toast.makeText(applicationContext, "Hello", Toast.LENGTH_SHORT).show()
        val intent = Intent(MainActivity@this, AddDeckActivity::class.java)
        startActivity(intent)
    }
}