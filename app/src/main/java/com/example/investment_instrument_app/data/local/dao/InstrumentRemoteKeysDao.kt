package com.example.investment_instrument_app.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.investment_instrument_app.domain.model.InstrumentRemoteKeys

interface InstrumentRemoteKeysDao {

    @Query("SELECT * FROM instrument_remote_keys_table WHERE id = :instrumentId")
    suspend fun getRemoteKeys(instrumentId: Int): InstrumentRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(instrumentRemoteKeys: List<InstrumentRemoteKeys>)

    @Query("DELETE FROM instrument_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}