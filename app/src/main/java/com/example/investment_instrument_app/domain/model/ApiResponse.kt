package com.example.investment_instrument_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val instruments: List<Instrument> = emptyList(),
    val lastUpdated: Long? = null
)
