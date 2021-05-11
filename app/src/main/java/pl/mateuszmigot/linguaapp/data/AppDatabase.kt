package pl.mateuszmigot.linguaapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mateuszmigot.linguaapp.models.Deck

@Database(entities = arrayOf(Deck::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDao
}