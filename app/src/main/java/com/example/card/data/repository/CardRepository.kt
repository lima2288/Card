package com.example.card.data.repository

import com.example.card.network.CardApi
import com.example.card.data.model.Card

class CardRepository {
    suspend fun getCreditCards(): List<Card> {
        // API call to fetch card list
        return CardApi.getCreditCards()
    }
}