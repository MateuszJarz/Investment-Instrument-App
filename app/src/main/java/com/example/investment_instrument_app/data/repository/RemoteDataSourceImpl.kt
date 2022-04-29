package com.example.investment_instrument_app.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.investment_instrument_app.data.local.InvestmentInstrumentDatabase
import com.example.investment_instrument_app.data.paging_source.InstrumentRemoteMediator
import com.example.investment_instrument_app.data.remote.InvestmentInstrumentApi
import com.example.investment_instrument_app.domain.model.Instrument
import com.example.investment_instrument_app.domain.repository.RemoteDataSource
import com.example.investment_instrument_app.untill.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val investmentApi: InvestmentInstrumentApi,
    private val investmentDatabase: InvestmentInstrumentDatabase

) : RemoteDataSource {

    private val instrumentDao = investmentDatabase.instrumentDao()

    override fun getAllInstruments(): Flow<PagingData<Instrument>> {
        val pagingSourceFactory = { instrumentDao.getAllInstruments() }

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = InstrumentRemoteMediator(
                instrumentApi = investmentApi,
                investmentInstrumentDatabase = investmentDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }

    override fun searchInstruments(): Flow<PagingData<Instrument>> {
        TODO("Not yet implemented")
    }
}