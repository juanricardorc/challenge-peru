package com.juanricardorc.uicomponents.exchangerate

import android.view.View

interface ExchangeRateBelowButtonListener {
    fun onBelowValue(view: View, value: String)
}