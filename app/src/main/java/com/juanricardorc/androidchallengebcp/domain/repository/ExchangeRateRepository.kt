package com.juanricardorc.androidchallengebcp.domain.repository

import com.juanricardorc.androidchallengebcp.datasource.response.ExChangeRateResponse

interface ExchangeRateRepository {
    suspend fun getExchangeRateUsd(monetary: String): ExChangeRateResponse
    suspend fun getExchangeRatePen(monetary: String): ExChangeRateResponse
    suspend fun getExchangeRateMxn(monetary: String): ExChangeRateResponse
}