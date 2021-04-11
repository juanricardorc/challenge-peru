package com.juanricardorc.androidchallengebcp.datasource.response

import com.google.gson.annotations.SerializedName

data class ExChangeRateResponse(
    @SerializedName("monetary") var monetary: String,
    @SerializedName("rates") var rates: List<RateResponse>
)