// Package declaration
package com.jetbrains.handson.json.android

// Imports necessary for the Android application
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

// Importing classes from other packages or files
import com.jetbrains.handson.json.DatabaseDriverFactory
import com.jetbrains.handson.json.Greeting
import com.jetbrains.handson.json.SharedModule
import com.jetbrains.handson.json.api.AccountBalance
import com.jetbrains.handson.json.api.AccountTransaction
import com.jetbrains.handson.json.service.AccountServiceImpl

// Main activity class definition
class MainActivity : ComponentActivity() {
    // Late initialization of database driver factory
    private lateinit var databaseDriverFactory: DatabaseDriverFactory

    // onCreate function, called when activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initializing database driver factory
        databaseDriverFactory = DatabaseDriverFactory(applicationContext)
        // Setting content of the activity using Jetpack Compose
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(Color.White),
                    color = Color.White,
                ) {
                    // Displaying a greeting message
                    GreetingView(Greeting().greet())
                    // Processing some data from SharedModule
                    SharedModule.processData("this is data")
                    // Launching effect to load mock data asynchronously
                    LaunchedEffect(Unit) {
                        loadMockData()
                    }
                }
            }
        }
    }

    // Function to load mock data
    private fun loadMockData() {
        MockLoader(this).init()
    }

    // Composable function to display greeting view
    @Composable
    fun GreetingView(text: String) {
        // Initializing account service
        val accountService = AccountServiceImpl(databaseDriverFactory)
        // Mutable state to hold list of account balances
        var balances: List<AccountBalance>? by remember { mutableStateOf(null) }
        // Fetching account balances from account service
        val accountBalanceList = accountService.getBalanceServices()
        balances = accountBalanceList
        // Displaying balances in a card
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
                // Displaying 'Balances' text
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

                // Looping through account balances to display each balance
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
                                    // Displaying 'Available Balance' and its value
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
                                    // Displaying 'Current Balance' and its value
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

    // Preview function for the composable
    @Preview
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
            GreetingView("Hello, Android!")
        }
    }
}
