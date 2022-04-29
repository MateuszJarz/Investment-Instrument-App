package com.example.investment_instrument_app.data.repository

import androidx.paging.PagingData
import com.example.investment_instrument_app.domain.model.Instrument
import com.example.investment_instrument_app.domain.repository.LocalDataSource
import com.example.investment_instrument_app.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local : LocalDataSource

    ) {

    fun getAllHeroes(): Flow<PagingData<Instrument>> {
        return remote.getAllInstruments()
    }

    suspend fun getSelectedInstrument(InstrumentId: Int): Instrument {
        return local.getSelectedInstrument(instrumentId = InstrumentId)
    }



}