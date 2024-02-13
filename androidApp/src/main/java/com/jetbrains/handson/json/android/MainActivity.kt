package com.jetbrains.handson.json.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.handson.json.DatabaseDriverFactory
import com.jetbrains.handson.json.Greeting
import com.jetbrains.handson.json.SharedModule
import com.jetbrains.handson.json.api.AccountBalance
import com.jetbrains.handson.json.api.AccountTransaction
import com.jetbrains.handson.json.service.AccountServiceImpl

class MainActivity : ComponentActivity() {
    private lateinit var databaseDriverFactory: DatabaseDriverFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseDriverFactory = DatabaseDriverFactory(applicationContext)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(Color.White),
                    color = Color.White,
                ) {
                    GreetingView(Greeting().greet())
                    SharedModule.processData("this ius data")
                    LaunchedEffect(Unit) {
                        loadMockData()
                    }
                }
            }
        }

    }

    private fun loadMockData() {
        MockLoader(this).init()
    }


    @Composable
    fun GreetingView(text: String) {

        val accountService = AccountServiceImpl(databaseDriverFactory)
        var balances: List<AccountBalance>? by remember { mutableStateOf(null) }
        val accountBalanceList = accountService.getBalanceServices()
        balances = accountBalanceList
        Card(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth().background(Color.White),
            shape = MaterialTheme.shapes.large,


        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = "Balances",
                            style = MaterialTheme.typography.bodyMedium
                                .copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                ),
                        )
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))

                for (balance in balances!!) {

                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Column {
                                Row(
                                    Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = "Available Balance",
                                        style = MaterialTheme.typography.bodyMedium
                                            .copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                            ),
                                    )

                                    Text(
                                        text = balance.cashBalance.toString(),
                                        style = MaterialTheme.typography.bodyMedium
                                            .copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                            ),
                                        modifier = Modifier.align(Alignment.CenterVertically),
                                    )
                                }
                                Row(
                                    Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = "Current Balance",
                                        style = MaterialTheme.typography.bodyMedium
                                            .copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                            ),
                                    )

                                    Text(
                                        text = balance.currentBalance.toString(),
                                        style = MaterialTheme.typography.bodyMedium
                                            .copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                            ),
                                        modifier = Modifier . align (Alignment.CenterVertically),
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

        }

    }

    @Preview
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
            GreetingView("Hello, Android!")
        }
    }
}


