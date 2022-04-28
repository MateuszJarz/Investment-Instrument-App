package com.example.investment_instrument_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.investment_instrument_app.data.local.dao.InstrumentDao
import com.example.investment_instrument_app.data.local.dao.InstrumentRemoteKeysDao
import com.example.investment_instrument_app.domain.model.Instrument
import com.example.investment_instrument_app.domain.model.InstrumentRemoteKeys

@Database(entities = [Instrument::class,InstrumentRemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class InvestmentInstrumentDatabase : RoomDatabase() {
    abstract fun instrumentDao(): InstrumentDao
    abstract fun instrumentRemoteKeysDao(): InstrumentRemoteKeysDao


}