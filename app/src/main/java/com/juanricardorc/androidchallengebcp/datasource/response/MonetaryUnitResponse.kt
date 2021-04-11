package com.juanricardorc.androidchallengebcp.datasource.response

import com.google.gson.annotations.SerializedName

data class MonetaryUnitResponse(
    @SerializedName("country") var country: String,
    @SerializedName("monetary") var monetary: String
)