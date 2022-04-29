package com.example.investment_instrument_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.investment_instrument_app.untill.Constants.INSTRUMENT_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = INSTRUMENT_DATABASE_TABLE)
data class Instrument(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val name: String,
    val about: String,
    val rating: Double,
    val family: List<String>,
)
