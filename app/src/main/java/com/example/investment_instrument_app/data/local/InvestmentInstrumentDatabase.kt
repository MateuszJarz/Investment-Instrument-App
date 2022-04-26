package com.example.investment_instrument_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.investment_instrument_app.domain.model.Instrument

@Database(entities = [Instrument::class], version = 1)
abstract class InvestmentInstrumentDatabase :RoomDatabase(){



}