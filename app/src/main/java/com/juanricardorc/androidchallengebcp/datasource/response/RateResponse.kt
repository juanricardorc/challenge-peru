package com.juanricardorc.androidchallengebcp.datasource.response

import com.google.gson.annotations.SerializedName

data class RateResponse(
    @SerializedName("name") var name: String,
    @SerializedName("monetary") var monetary: String,
    @SerializedName("value") var value: Float)