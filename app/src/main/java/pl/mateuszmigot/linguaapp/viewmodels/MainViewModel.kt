package pl.mateuszmigot.linguaapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.mateuszmigot.linguaapp.data.DataSource

class MainViewModel(val dataSource: DataSource) : ViewModel() {

    val deckLiveData = dataSource.getDeckList()

    class DeckListViewModelFactory(private val context: Context): ViewModelProvider.Factory {
        override fun<T: ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(
                    dataSource = DataSource.getDataSource(context.resources)
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}