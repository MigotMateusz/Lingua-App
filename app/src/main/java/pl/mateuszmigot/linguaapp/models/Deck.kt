package pl.mateuszmigot.linguaapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Deck(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "percent_done") val percent: Int,
    @ColumnInfo(name = "cards_numbers") val cardNumber: Int
)

