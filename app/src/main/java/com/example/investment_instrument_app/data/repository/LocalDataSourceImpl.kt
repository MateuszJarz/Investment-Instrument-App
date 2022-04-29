package com.example.investment_instrument_app.data.repository

import com.example.investment_instrument_app.data.local.InvestmentInstrumentDatabase
import com.example.investment_instrument_app.domain.model.Instrument
import com.example.investment_instrument_app.domain.repository.LocalDataSource

class LocalDataSourceImpl(investmentDatabase: InvestmentInstrumentDatabase): LocalDataSource {

    private val investmentDao = investmentDatabase.instrumentDao()

    override suspend fun getSelectedInstrument(instrumentId: Int): Instrument {
        return investmentDao.getSelectedInstrument(instrumentId = instrumentId)
    }
}