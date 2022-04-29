package com.example.investment_instrument_app.domain.use_case

import com.example.investment_instrument_app.domain.use_case.get_all_instrument.GetAllInstrumentsUseCase
import com.example.investment_instrument_app.domain.use_case.get_selected_instrument.GetSelectedInstrument

class UseCase(
    val getAllInstrumentsUseCase: GetAllInstrumentsUseCase,
    val getSelectedInstrument: GetSelectedInstrument
)