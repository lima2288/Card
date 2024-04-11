package com.example.card.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.card.ui.viewmodel.CardListViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.test.R


//class MainActivity : ComponentActivity() {
class CardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = CardListViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CardListScreen(vm)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(cardListViewModel: CardListViewModel) {

    val cardListState by cardListViewModel.cardListState
    val isLoading by cardListViewModel.isLoading
    val errorMessage = cardListViewModel.errorMessage

    LaunchedEffect(key1 = Unit) {
        // Load list when the screen is first launched
        cardListViewModel.loadCardList()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            // Show loading indicator
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            // Show card list
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Row(
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .fillMaxWidth()
                                    .padding(8.dp, 8.dp, 0.dp, 8.dp)
                            ) {
                                Text("Credit Cards")
                            }
                        },
                        modifier = Modifier.padding(0.dp, 0.dp, 16.dp, 0.dp)
                    )
                },
                content = {
                    if (errorMessage.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp, 36.dp, 16.dp, 16.dp)
                                .background(Color(android.graphics.Color.parseColor("#f2f2f2")))
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(0.dp, 30.dp, 0.dp, 16.dp)
                            ) {
                                items(cardListState) { card ->
                                    Column {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                            ) {
                                                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                                                    Text(
                                                        card.credit_card_number,
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis,
                                                    )

                                                    Text(
                                                        card.credit_card_type,
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis,
                                                    )

                                                }


                                                Spacer(modifier = Modifier.width(16.dp))
                                                Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                                                    Text(
                                                        stringResource(R.string.expires_on),
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis,
                                                    )
                                                    Text(
                                                        card.credit_card_expiry_date,
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis,
                                                    )
                                                }

                                            }
                                        }
                                        Divider()
                                    }
                                }
                            }
                        }
                    } else {
                        Text(errorMessage)
                    }
                }
            )
        }
    }
}
