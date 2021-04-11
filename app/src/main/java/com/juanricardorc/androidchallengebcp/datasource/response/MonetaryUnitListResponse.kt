package com.juanricardorc.androidchallengebcp.datasource.response

import com.google.gson.annotations.SerializedName

data class MonetaryUnitListResponse(
    @SerializedName("monetaryUnits") var monetaryUnits: List<MonetaryUnitResponse>
)