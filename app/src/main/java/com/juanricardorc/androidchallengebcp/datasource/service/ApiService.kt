package com.juanricardorc.androidchallengebcp.datasource.service

import com.juanricardorc.androidchallengebcp.datasource.response.ExChangeRateResponse
import com.juanricardorc.androidchallengebcp.datasource.response.ListMonetaryUnitResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("monetary-unit")
    suspend fun getMonetaryUnitListResponse(): Response<ListMonetaryUnitResponse>

    @GET("exchange-rate/usd")
    suspend fun getExchangeRateUsd(): Response<ExChangeRateResponse>

    @GET("exchange-rate/pen")
    suspend fun getExchangeRatePen(): Response<ExChangeRateResponse>

    @GET("exchange-rate/mxn")
    suspend fun getExchangeRateMxn(): Response<ExChangeRateResponse>

    @GET("exchange-rate/Brl")
    suspend fun getExchangeRateBrl(): Response<ExChangeRateResponse>

    @GET("exchange-rate/Eur")
    suspend fun getExchangeRateEur(): Response<ExChangeRateResponse>
}