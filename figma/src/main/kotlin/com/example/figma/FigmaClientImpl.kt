package com.example.figma

import com.example.figma.api.FigmaApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FigmaClientImpl(
    private val apiToken: String
) : FigmaClient {
    private val api: FigmaApi
    private val gson = Gson()

    init {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.figma.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(FigmaApi::class.java)
    }

    override suspend fun getFile(fileId: String): String {
        val response = api.getFile(fileId, apiToken)
        return gson.toJson(response)
    }

    override suspend fun getComments(fileId: String): String {
        val response = api.getComments(fileId, apiToken)
        return gson.toJson(response)
    }

    override suspend fun updateDesign(fileId: String, changes: Map<String, Any>): String {
        val response = api.updateDesign(fileId, apiToken, changes)
        return gson.toJson(response)
    }
} 