package com.example.investment_instrument_app.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.investment_instrument_app.domain.model.Instrument

@Dao
interface InstrumentDao {

    @Query("SELECT * FROM instrument_table ORDER BY id ASC")
    fun getAllInstruments(): PagingSource<Int, Instrument>

    @Query("SELECT * FROM instrument_table WHERE id=:instrumentId")
    fun getSelectedInstrument(instrumentId: Int): Instrument

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInstrument(instruments: List<Instrument>)

    @Query("DELETE FROM instrument_table")
    suspend fun deleteAllInstruments()

}