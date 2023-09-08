package com.hg.cartelera.core

import android.util.Log
import com.google.gson.GsonBuilder
import com.hg.cartelera.network.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    val webService: WebService by lazy {
        Log.i("Retrofit", "OK1 ")
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}