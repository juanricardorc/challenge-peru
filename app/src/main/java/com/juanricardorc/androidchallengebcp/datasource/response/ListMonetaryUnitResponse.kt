package com.juanricardorc.androidchallengebcp.datasource.response

import com.google.gson.annotations.SerializedName

data class ListMonetaryUnitResponse(
    @SerializedName("monetaryUnits") var monetaryUnits: List<MonetaryUnitResponse>
)