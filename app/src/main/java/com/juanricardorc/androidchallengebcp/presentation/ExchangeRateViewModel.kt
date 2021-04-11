package com.juanricardorc.androidchallengebcp.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanricardorc.androidchallengebcp.datasource.data.ExchangeRateNetworkDataSource
import com.juanricardorc.androidchallengebcp.datasource.network.ExchangeRateClient
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse
import com.juanricardorc.androidchallengebcp.datasource.service.ApiService
import com.juanricardorc.androidchallengebcp.domain.repository.ExchangeRateRepository
import com.juanricardorc.uicomponents.exchangerate.MXN
import com.juanricardorc.uicomponents.exchangerate.PEN
import com.juanricardorc.uicomponents.exchangerate.TWO
import com.juanricardorc.uicomponents.exchangerate.USD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Collectors

class ExchangeRateViewModel : ViewModel() {
    private var aboveValue: MutableLiveData<MonetaryUnitResponse> = MutableLiveData()
    private var belowValue: MutableLiveData<MonetaryUnitResponse> = MutableLiveData()
    private var result: MutableLiveData<Float> = MutableLiveData()
    private var listMonetaryUnitResponse: MutableLiveData<List<MonetaryUnitResponse>> =
        MutableLiveData()

    fun getAboveValue(): LiveData<MonetaryUnitResponse> {
        return aboveValue
    }

    fun getBelowValue(): LiveData<MonetaryUnitResponse> {
        return belowValue
    }

    fun getListMonetaryUnitResponse(): LiveData<List<MonetaryUnitResponse>> {
        return listMonetaryUnitResponse
    }

    fun getListMonetaryUnit(context: Context) {
        var apiService = ExchangeRateClient
            .getInstance(context)
            .createService(ApiService::class.java)
        var exchangeRateRepository =
            ExchangeRateNetworkDataSource(
                apiService
            )
        viewModelScope.launch(Dispatchers.IO) {
            listMonetaryUnitResponse.postValue(exchangeRateRepository.getListMonetaryUnit())
            setupMonetaryUnit(exchangeRateRepository.getListMonetaryUnit())
        }
    }

    private fun setupMonetaryUnit(listMonetaryUnitResponse: List<MonetaryUnitResponse>) {
        if (listMonetaryUnitResponse.size >= TWO) {
            val shuffled = listMonetaryUnitResponse.shuffled()
            aboveValue.postValue(shuffled[0])
            belowValue.postValue(shuffled[1])
        }
    }

    fun getExchangeRate(monetary: String, context: Context) {
        var apiService = ExchangeRateClient
            .getInstance(context)
            .createService(ApiService::class.java)
        var exchangeRateRepository =
            ExchangeRateNetworkDataSource(
                apiService
            )

        when (monetary) {
            USD -> {
                getExchangeRateUsd(monetary, exchangeRateRepository)
            }
            PEN -> {
                getExchangeRatePen(monetary, exchangeRateRepository)
            }
            MXN -> {
                getExchangeRateMxn(monetary, exchangeRateRepository)
            }
            else -> {
                other()
            }
        }
    }

    private fun getExchangeRateUsd(
        monetary: String,
        exchangeRateRepository: ExchangeRateRepository
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var exChangeRateResponse = exchangeRateRepository
                .getExchangeRateUsd(monetary)
            val rates = exChangeRateResponse.rates
            val filter = rates.stream().filter {
                it.monetary == getBelowValue().value!!.monetary
            }.collect(Collectors.toList())

            if (filter.size >= 1) {
                val value = filter[0].value
                result.postValue(value)
            }
        }
    }

    private fun getExchangeRatePen(
        monetary: String,
        exchangeRateRepository: ExchangeRateRepository
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var exChangeRateResponse = exchangeRateRepository
                .getExchangeRateUsd(monetary)
            val rates = exChangeRateResponse.rates
            val filter = rates.stream().filter {
                it.monetary == getBelowValue().value!!.monetary
            }.collect(Collectors.toList())

            if (filter.size >= 1) {
                val value = filter[0].value
                result.postValue(value)
            }
        }
    }

    private fun getExchangeRateMxn(
        monetary: String,
        exchangeRateRepository: ExchangeRateRepository
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var exChangeRateResponse = exchangeRateRepository
                .getExchangeRateUsd(monetary)
            val rates = exChangeRateResponse.rates
            val filter = rates.stream().filter {
                it.monetary == getBelowValue().value!!.monetary
            }.collect(Collectors.toList())

            if (filter.size >= 1) {
                val value = filter[0].value
                result.postValue(value)
            }
        }
    }

    private fun other() {
        result.postValue(0.00f)
    }

    fun getResult(): LiveData<Float> {
        return this.result
    }
}