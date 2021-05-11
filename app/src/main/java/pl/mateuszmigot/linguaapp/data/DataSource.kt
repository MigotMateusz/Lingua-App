package pl.mateuszmigot.linguaapp.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.mateuszmigot.linguaapp.models.Deck

class DataSource {
    val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "lingua-database").build()
    val deckDao = db.deckDao()
    val deckList: List<Deck> = deckDao.getAll()
    /*private val deckList : List<Deck> = listOf(
        Deck(name = "Talia 1", percent = 25, cardNumber = 10),
        Deck(name = "Talia 2", percent = 25, cardNumber = 10),
        Deck(name = "Talia 3", percent = 25, cardNumber = 10)
    )*/
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
                val newInstance = INSTANCE ?: DataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }

}