package com.arch.starwarssearch.remote

import com.arch.starwarssearch.remote.api.StarWarsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseTest {
    private lateinit var mockWebServer: MockWebServer

    lateinit var starWarsService: StarWarsService

    @Before
    open fun setup(){
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = StarWarsRequestDispatcher()
        mockWebServer.start()
        starWarsService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}