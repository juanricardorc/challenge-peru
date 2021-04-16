package com.juanricardorc.androidchallengebcp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.juanricardorc.androidchallengebcp.databinding.ActivityMainBinding
import com.juanricardorc.androidchallengebcp.datasource.data.ExchangeRateNetworkDataSource
import com.juanricardorc.androidchallengebcp.datasource.network.ExchangeRateClient
import com.juanricardorc.androidchallengebcp.datasource.service.ApiService
import com.juanricardorc.androidchallengebcp.domain.usecase.GetListMonetaryUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var exchangeRateViewModel: ExchangeRateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeExchangeRateViewModel()
    }

    private fun initializeExchangeRateViewModel() {
        val apiService = ExchangeRateClient.getInstance(context = baseContext)
            .createService(ApiService::class.java)
        val exchangeRateRepository = ExchangeRateNetworkDataSource(apiService)
        val getListMonetaryUnit = GetListMonetaryUnit(exchangeRateRepository)
        val exchangeRateViewModelFactory = ExchangeRateViewModelFactory(getListMonetaryUnit)
        exchangeRateViewModel = ViewModelProvider(
            this, exchangeRateViewModelFactory
        ).get(ExchangeRateViewModel::class.java)

    }
}