package com.example.investment_instrument_app.navigation



    sealed class Screen(val route: String) {
        object Splash : Screen("splash_screen")
        object Welcome : Screen("welcome_screen")
        object Home : Screen("home_screen")
        object Details : Screen("details_screen/{instrumentId}") {
            fun passInstrumentId(instrumentId: Int): String {
                return "details_screen/$instrumentId"
            }
        }

        object Search : Screen("search_screen")
    }
