package com.juanricardorc.androidchallengebcp.datasource.data

import com.juanricardorc.androidchallengebcp.datasource.response.ExChangeRateResponse
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse
import com.juanricardorc.androidchallengebcp.datasource.service.ApiService
import com.juanricardorc.androidchallengebcp.domain.repository.ExchangeRateRepository

class ExchangeRateNetworkDataSource :
    ExchangeRateRepository {

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

    override suspend fun getExchangeRateUsd(monetary: String): ExChangeRateResponse? {
        val exchangeRate = this.apiService.getExchangeRateUsd()
        return exchangeRate.body()!!
    }

    override suspend fun getExchangeRatePen(monetary: String): ExChangeRateResponse? {
        val exchangeRate = this.apiService.getExchangeRatePen()
        return exchangeRate.body()
    }

    override suspend fun getExchangeRateMxn(monetary: String): ExChangeRateResponse? {
        val exchangeRate = this.apiService.getExchangeRateMxn()
        return exchangeRate.body()
    }

    override suspend fun getExchangeRateBrl(monetary: String): ExChangeRateResponse? {
        val exchangeRate = this.apiService.getExchangeRateBrl()
        return exchangeRate.body()
    }

    override suspend fun getExchangeRateEur(monetary: String): ExChangeRateResponse? {
        val exchangeRate = this.apiService.getExchangeRateEur()
        return exchangeRate.body()
    }
}