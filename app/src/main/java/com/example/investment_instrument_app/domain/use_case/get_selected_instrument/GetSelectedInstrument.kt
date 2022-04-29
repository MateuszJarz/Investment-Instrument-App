package com.example.investment_instrument_app.domain.use_case.get_selected_instrument

import com.example.investment_instrument_app.data.repository.Repository
import com.example.investment_instrument_app.domain.model.Instrument

class GetSelectedInstrument(
    private val repository: Repository
) {
    suspend operator fun invoke(instrumentId: Int): Instrument{
        return repository.getSelectedInstrument(instrumentId)
    }
}