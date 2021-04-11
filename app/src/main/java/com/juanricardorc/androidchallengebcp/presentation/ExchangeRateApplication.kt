package com.juanricardorc.androidchallengebcp.presentation

import android.app.Application
import com.juanricardorc.androidchallengebcp.datasource.network.ExchangeRateClient
import com.juanricardorc.androidchallengebcp.datasource.service.ApiService

class ExchangeRateApplication : Application() {
    fun getApiService(): ApiService {
        return ExchangeRateClient.getInstance(baseContext).createService(ApiService::class.java)
    }
}