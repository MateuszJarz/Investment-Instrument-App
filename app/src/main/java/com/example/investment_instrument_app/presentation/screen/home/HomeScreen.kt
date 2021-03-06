package com.example.investment_instrument_app.presentation.screen.home

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.investment_instrument_app.navigation.Screen
import com.example.investment_instrument_app.presentation.common.ListContent

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allInstruments = homeViewModel.getAllInstruments.collectAsLazyPagingItems()
        Log.d("All instrument" , allInstruments.itemCount.toString())
    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        }, content = {
            ListContent(instruments = allInstruments, navController = navController)
        }
    )
}