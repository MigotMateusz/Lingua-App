package pl.mateuszmigot.linguaapp.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.mateuszmigot.linguaapp.models.Deck

class DataSource(resources: Resources) {
    private val deckList : List<Deck> = listOf(
        Deck(name = "Talia 1"),
        Deck(name = "Talia 2"),
        Deck(name = "Talia 3")
    )
    private val deckLiveData = MutableLiveData(deckList)

    fun addDeck(deck: Deck) {
        val currentList = deckLiveData.value
        if(currentList == null) {
            deckLiveData.postValue(deckList)
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, deck)
            deckLiveData.postValue(updatedList)
        }
    }

    fun getDeckList() : LiveData<List<Deck>> {
        return deckLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null
        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

}