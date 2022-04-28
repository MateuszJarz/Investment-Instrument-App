package com.example.investment_instrument_app.domain.repository

import androidx.paging.PagingData
import com.example.investment_instrument_app.domain.model.Instrument
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllInstruments(): Flow<PagingData<Instrument>>
    fun searchInstruments(): Flow<PagingData<Instrument>>
}