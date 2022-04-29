package com.example.investment_instrument_app.presentation.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.investment_instrument_app.domain.model.Instrument
import com.example.investment_instrument_app.domain.use_case.UseCase
import com.example.investment_instrument_app.untill.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedInstrument: MutableStateFlow<Instrument?> = MutableStateFlow(null)
    val selectedInstrument: StateFlow<Instrument?> = _selectedInstrument

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val instrumentId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedInstrument.value =
                instrumentId?.let { useCases.getSelectedInstrument(instrumentId = instrumentId) }
        }
    }
}