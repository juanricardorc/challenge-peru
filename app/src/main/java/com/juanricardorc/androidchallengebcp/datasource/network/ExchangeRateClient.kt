package com.juanricardorc.androidchallengebcp.datasource.network

import android.content.Context
import com.juanricardorc.androidchallengebcp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ExchangeRateClient() : ApiClient() {
    private lateinit var retrofit: Retrofit
    private lateinit var builder: OkHttpClient.Builder
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var logging: HttpLoggingInterceptor
    private lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
        setup()
    }

    private fun setup() {
        logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder = getOkHttpClient()
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor(logging)
        okHttpClient = builder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .addConverterFactory(GsonConverterFactory.create())

            .client(okHttpClient)
            .build()
    }

    companion object {
        fun getInstance(context: Context): ExchangeRateClient {
            return ExchangeRateClient(context)
        }
    }

    override fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}