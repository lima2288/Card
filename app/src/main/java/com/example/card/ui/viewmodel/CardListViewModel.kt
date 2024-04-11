package com.example.card.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.card.data.model.Card
import com.example.card.data.repository.CardRepository
import kotlinx.coroutines.launch

open class CardListViewModel : ViewModel() {
    private val repository = CardRepository()

    // State for the list of Cards
    val cardListState = mutableStateOf<List<Card>>(emptyList())
    val isLoading = mutableStateOf(false)
    var errorMessage: String by mutableStateOf("")

    fun loadCardList() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val card = repository.getCreditCards()
                cardListState.value = card

            } catch (e: Exception) {
                // Handle error
            } finally {
                isLoading.value = false
            }
        }
    }
}