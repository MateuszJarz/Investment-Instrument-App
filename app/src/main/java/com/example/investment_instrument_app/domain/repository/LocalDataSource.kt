package com.example.investment_instrument_app.domain.repository

import com.example.investment_instrument_app.domain.model.Instrument

interface LocalDataSource {
    suspend fun getSelectedInstrument(instrumentId: Int): Instrument

}