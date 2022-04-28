package com.example.investment_instrument_app.domain.use_case.get_all_instrument

import androidx.paging.PagingData
import com.example.investment_instrument_app.data.repository.Repository
import com.example.investment_instrument_app.domain.model.Instrument
import kotlinx.coroutines.flow.Flow


class GetAllInstrumentsUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Instrument>> {
        return repository.getAllHeroes()
    }
}