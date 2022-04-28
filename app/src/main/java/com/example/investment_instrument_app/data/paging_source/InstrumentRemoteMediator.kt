package com.example.investment_instrument_app.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.investment_instrument_app.data.local.InvestmentInstrumentDatabase
import com.example.investment_instrument_app.data.remote.InvestmentInstrumentApi
import com.example.investment_instrument_app.domain.model.Instrument
import com.example.investment_instrument_app.domain.model.InstrumentRemoteKeys
import java.lang.Exception
import javax.inject.Inject

@ExperimentalPagingApi
class InstrumentRemoteMediator @Inject constructor(
    private val instrumentApi: InvestmentInstrumentApi,
    private val instrumentDatabase: InvestmentInstrumentDatabase
) : RemoteMediator<Int,Instrument>(){

    private val instrumentDao = instrumentDatabase.instrumentDao()
    private val instrumentRemoteKeysDao = instrumentDatabase.instrumentRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Instrument>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = instrumentApi.getAllInstruments(page = page)

            if (response.instruments.isNotEmpty()) {
                instrumentDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        instrumentDao.deleteAllInstruments()
                        instrumentRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.instruments.map { instrument ->
                        InstrumentRemoteKeys(
                            id = instrument.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    instrumentRemoteKeysDao.addAllRemoteKeys(instrumentRemoteKeys = keys)
                    instrumentDao.addInstrument(instruments = response.instruments)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Instrument>
    ): InstrumentRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                instrumentRemoteKeysDao.getRemoteKeys(instrumentId = id)
            }
        }
    }
    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Instrument>
    ): InstrumentRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { instrument ->
                instrumentRemoteKeysDao.getRemoteKeys(instrumentId = instrument.id)
            }
    }
    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Instrument>
    ): InstrumentRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { instrument ->
                instrumentRemoteKeysDao.getRemoteKeys(instrumentId = instrument.id)
            }
    }
}