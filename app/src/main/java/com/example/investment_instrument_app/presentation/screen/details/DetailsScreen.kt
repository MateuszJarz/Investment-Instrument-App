package com.example.investment_instrument_app.presentation.screen.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedInstrument by detailsViewModel.selectedInstrument.collectAsState()

    DetailsContent(
        navController = navController,
        selectedInstrument = selectedInstrument
    )
}