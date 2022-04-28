package com.example.investment_instrument_app.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.investment_instrument_app.domain.use_case.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCase: UseCase
): ViewModel() {
    val getAllInstruments = useCase.getAllInstrumentsUseCase()
}