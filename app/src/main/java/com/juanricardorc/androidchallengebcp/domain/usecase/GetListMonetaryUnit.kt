package com.juanricardorc.androidchallengebcp.domain.usecase

import com.juanricardorc.androidchallengebcp.datasource.response.MonetaryUnitResponse
import com.juanricardorc.androidchallengebcp.domain.repository.ExchangeRateRepository

class GetListMonetaryUnit(private val exchangeRateRepository: ExchangeRateRepository) {
    suspend operator fun invoke(): List<MonetaryUnitResponse> = exchangeRateRepository
        .getListMonetaryUnit()
}