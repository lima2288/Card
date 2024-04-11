package com.example.card.network

import com.example.card.data.model.Card
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CardApiService {
    @GET("credit_cards")
    suspend fun getCreditCards(@Query("size") size: Int = 100): List<Card>
}

private const val BASE_URL = "https://random-data-api.com/api/v2/"

object CardApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CardApiService::class.java)

    suspend fun getCreditCards(): List<Card> {
        return service.getCreditCards()
    }
}