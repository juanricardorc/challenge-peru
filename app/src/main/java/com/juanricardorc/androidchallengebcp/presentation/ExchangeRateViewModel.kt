package com.juanricardorc.androidchallengebcp.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanricardorc.androidchallengebcp.datasource.data.ExchangeRateNetworkDataSource
import com.juanricardorc.androidchallengebcp.datasource.network.ExchangeRateClient
import com.juanricardorc.androidchallengebcp.datasource.response.ExChangeRateResponse
import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse
import com.juanricardorc.androidchallengebcp.datasource.response.RateResponse
import com.juanricardorc.androidchallengebcp.datasource.service.ApiService
import com.juanricardorc.androidchallengebcp.domain.repository.ExchangeRateRepository
import com.juanricardorc.uicomponents.exchangerate.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Collectors

class ExchangeRateViewModel : ViewModel() {
    private var aboveValue: MutableLiveData<MonetaryUnitResponse> = MutableLiveData()
    private var belowValue: MutableLiveData<MonetaryUnitResponse> = MutableLiveData()
    private var rateResponse: MutableLiveData<RateResponse> = MutableLiveData()
    private var listMonetaryUnitResponse: MutableLiveData<List<MonetaryUnitResponse>> =
        MutableLiveData()
    private var exchangeValue: Float = 0.0f

    fun getAboveValue(): LiveData<MonetaryUnitResponse> {
        return aboveValue
    }

    fun getBelowValue(): LiveData<MonetaryUnitResponse> {
        return belowValue
    }

    fun setAboveValue(monetaryUnitResponse: MonetaryUnitResponse) {
        this.aboveValue.postValue(monetaryUnitResponse)
    }

    fun setBelowValue(monetaryUnitResponse: MonetaryUnitResponse) {
        this.belowValue.postValue(monetaryUnitResponse)
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
            BRL -> {
                getExchangeRateBrl(monetary, exchangeRateRepository)
            }
            EUR -> {
                getExchangeRateEur(monetary, exchangeRateRepository)
            }
            else -> {
                other()
            }
        }
    }

    private fun getExchangeRateEur(
        monetary: String,
        exchangeRateRepository: ExchangeRateRepository
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var exChangeRateResponse = exchangeRateRepository
                .getExchangeRateEur(monetary)
            exChangeRateResponse?.let { setupResult(it) }
        }
    }

    private fun getExchangeRateBrl(
        monetary: String,
        exchangeRateRepository: ExchangeRateRepository
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var exChangeRateResponse = exchangeRateRepository
                .getExchangeRateBrl(monetary)
            exChangeRateResponse?.let { setupResult(it) }
        }
    }

    private fun getExchangeRateUsd(
        monetary: String,
        exchangeRateRepository: ExchangeRateRepository
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var exChangeRateResponse = exchangeRateRepository
                .getExchangeRateUsd(monetary)
            exChangeRateResponse?.let { setupResult(it) }
        }
    }

    private fun getExchangeRatePen(
        monetary: String,
        exchangeRateRepository: ExchangeRateRepository
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var exChangeRateResponse = exchangeRateRepository
                .getExchangeRatePen(monetary)
            exChangeRateResponse?.let { setupResult(it) }
        }
    }

    private fun getExchangeRateMxn(
        monetary: String,
        exchangeRateRepository: ExchangeRateRepository
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var exChangeRateResponse = exchangeRateRepository
                .getExchangeRateMxn(monetary)
            exChangeRateResponse?.let { setupResult(it) }
        }
    }

    private fun setupResult(exChangeRateResponse: ExChangeRateResponse) {
        val rates = exChangeRateResponse?.rates
        val filter = rates.stream().filter {
            it.monetary == getBelowValue().value!!.monetary
        }.collect(Collectors.toList())
        if (filter.size >= 1) {
            rateResponse.postValue(filter[0])
        }
    }

    private fun other() {
        //rateResponse.postValue(RateResponse("", "", 0.00f))
    }

    fun getRateResponse(): LiveData<RateResponse> {
        return this.rateResponse
    }

    fun getExchangeValue(): Float {
        return exchangeValue
    }

    fun setExchangeValue(exchangeValue: Float) {
        this.exchangeValue = exchangeValue
    }
}