package com.example.investment_instrument_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.investment_instrument_app.untill.Constants.INSTRUMENT_REMOTE_KEYS_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = INSTRUMENT_REMOTE_KEYS_DATABASE_TABLE)
data class InstrumentRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
