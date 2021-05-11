package pl.mateuszmigot.linguaapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pl.mateuszmigot.linguaapp.models.Deck

@Dao
interface DeckDao {
    @Query("SELECT * FROM deck")
    fun getAll(): List<Deck>

    @Insert
    fun insertAll(vararg decks: Deck)

    @Delete
    fun delete(deck: Deck)
}