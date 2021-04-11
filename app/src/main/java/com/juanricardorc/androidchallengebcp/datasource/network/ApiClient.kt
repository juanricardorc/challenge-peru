package com.juanricardorc.androidchallengebcp.datasource.network

import android.content.Context
import okhttp3.OkHttpClient

abstract class ApiClient {
    abstract fun <S> createService(serviceClass: Class<S>): S
    fun getOkHttpClient(context: Context): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }
}