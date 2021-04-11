package com.juanricardorc.uicomponents.exchangerate

import android.view.View

interface ExchangeRateUpdateButtonListener {
    fun onAboveValue(view: View, value: String)
}