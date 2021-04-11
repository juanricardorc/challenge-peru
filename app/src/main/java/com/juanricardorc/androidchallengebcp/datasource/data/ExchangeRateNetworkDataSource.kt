package com.juanricardorc.androidchallengebcp.datasource.data

import com.juanricardorc.androidchallengebcp.datasource.response.ExChangeRateResponse
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse
import com.juanricardorc.androidchallengebcp.datasource.service.ApiService
import com.juanricardorc.androidchallengebcp.repository.ExchangeRateRepository
import com.juanricardorc.uicomponents.exchangerate.MXN
import com.juanricardorc.uicomponents.exchangerate.PEN
import com.juanricardorc.uicomponents.exchangerate.USD

class ExchangeRateNetworkDataSource : ExchangeRateRepository {

    private var apiService: ApiService

    constructor(apiService: ApiService) {
        this.apiService = apiService
    }

    suspend fun getListMonetaryUnit(): List<MonetaryUnitResponse> {
        val monetaryUnitListResponse = this.apiService.getMonetaryUnitListResponse()
        return if (monetaryUnitListResponse.isSuccessful) {
            val body = monetaryUnitListResponse.body()
            body?.monetaryUnits ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getExchangeRateUsd(monetary: String): ExChangeRateResponse {
        val exchangeRateUsd = this.apiService.getExchangeRateUsd()
        return exchangeRateUsd.body()!!
    }

    override suspend fun getExchangeRatePen(monetary: String): ExChangeRateResponse {
        val exchangeRateUsd = this.apiService.getExchangeRatePen()
        return exchangeRateUsd.body()!!
    }

    override suspend fun getExchangeRateMxn(monetary: String): ExChangeRateResponse {
        val exchangeRateUsd = this.apiService.getExchangeRateMxn()
        return exchangeRateUsd.body()!!
    }
}