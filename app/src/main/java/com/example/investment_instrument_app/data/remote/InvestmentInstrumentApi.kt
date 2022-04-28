package com.example.investment_instrument_app.data.remote

import com.example.investment_instrument_app.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface InvestmentInstrumentApi {

    @GET("/investment/instruments")
    suspend fun getAllInstruments(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/investment/instruments/search")
    suspend fun searchInstruments(
        @Query("page") name: String
    ): ApiResponse
}