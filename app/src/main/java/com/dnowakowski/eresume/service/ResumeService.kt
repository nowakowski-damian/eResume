package com.dnowakowski.eresume.service

import com.dnowakowski.eresume.BuildConfig
import com.dnowakowski.eresume.BuildConfig.SERVER_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Damian Nowakowski on 24 January 2020
 */
object ResumeService {

    private val resumeApi: ResumeApi

    init {
        val client = OkHttpClient.Builder()

        if(BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addNetworkInterceptor(interceptor)
        }

        val retrofit = Retrofit.Builder()
            .client(client.build())
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        resumeApi = retrofit.create(ResumeApi::class.java)
    }

    fun api() = resumeApi
}