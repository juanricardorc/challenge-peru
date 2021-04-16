package com.juanricardorc.androidchallengebcp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juanricardorc.androidchallengebcp.domain.usecase.GetListMonetaryUnit

class ExchangeRateViewModelFactory(private val getListMonetaryUnit: GetListMonetaryUnit) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeRateViewModel::class.java)) {
            return ExchangeRateViewModel(getListMonetaryUnit) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}