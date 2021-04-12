package com.juanricardorc.uicomponents.exchangerate

import android.view.View

interface ExchangeButtonListener {
    fun onExchange(view: View, above: String, below: String)
}