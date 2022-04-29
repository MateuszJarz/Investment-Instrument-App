package com.example.investment_instrument_app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.investment_instrument_app.presentation.screen.details.DetailsScreen
import com.example.investment_instrument_app.presentation.screen.home.HomeScreen
import com.example.investment_instrument_app.untill.Constants.DETAILS_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Splash.route) {
            //SplashScreen()
        }
        composable(route = Screen.Welcome.route) {
            // WelcomeScreen()
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
                DetailsScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            //SearchScreen()
        }
    }
}