package com.juanricardorc.androidchallengebcp.domain.repository

import com.juanricardorc.androidchallengebcp.datasource.response.ExChangeRateResponse
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse

interface ExchangeRateRepository {
    suspend fun getListMonetaryUnit(): List<MonetaryUnitResponse>
    suspend fun getExchangeRateUsd(monetary: String): ExChangeRateResponse?
    suspend fun getExchangeRatePen(monetary: String): ExChangeRateResponse?
    suspend fun getExchangeRateMxn(monetary: String): ExChangeRateResponse?
    suspend fun getExchangeRateBrl(monetary: String): ExChangeRateResponse?
    suspend fun getExchangeRateEur(monetary: String): ExChangeRateResponse?
}