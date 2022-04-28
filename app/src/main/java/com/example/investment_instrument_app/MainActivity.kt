package com.example.investment_instrument_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.investment_instrument_app.navigation.SetupNavGraph
import com.example.investment_instrument_app.ui.theme.InvestmentInstrumentAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  
    private lateinit var navController: NavHostController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InvestmentInstrumentAppTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController )
            }
        }
    }
}

