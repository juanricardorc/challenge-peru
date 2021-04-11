package com.juanricardorc.androidchallengebcp.datasource.network

import okhttp3.OkHttpClient

abstract class ApiClient {
    abstract fun <S> createService(serviceClass: Class<S>): S
    fun getOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }
}