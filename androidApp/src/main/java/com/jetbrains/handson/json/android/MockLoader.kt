package com.jetbrains.handson.json.android

import android.annotation.SuppressLint
import android.content.Context
import com.jetbrains.handson.json.DatabaseDriverFactory
import com.jetbrains.handson.json.api.AccountBalance
import com.jetbrains.handson.json.api.AccountTransaction
import com.jetbrains.handson.json.service.MockService

import kotlinx.serialization.json.Json


class MockLoader(private val context: Context) {

    fun init() {
        var isLoaded = false
        if (isLoaded) {
            return
        }
        val databaseDriverFactory: DatabaseDriverFactory = DatabaseDriverFactory(context)
        val service = MockService(databaseDriverFactory)

        // account Transaction
        val jsonContent = loadJson("accounttransaction")
        // Deserialize JSON content into a list of AccountTransaction objects
        val transactions = Json.decodeFromString<List<AccountTransaction>>(jsonContent)
        service.loadAccountTransaction(transactions)

        // account Balance
        val balances = Json.decodeFromString<List<AccountBalance>>(loadJson("accountbalance"))
        service.loadAccountBalance(balances)


        true.also { isLoaded = it }
    }

    @SuppressLint("DiscouragedApi")
    fun loadJson(fileName: String): String {
        // Get the package name of the app
        val packageName = context.packageName
        // Get the resource ID of the raw resource file
        val resourceId = context.resources.getIdentifier(fileName, "raw", packageName)
        // Open an input stream to read the raw resource file
        val inputStream = context.resources.openRawResource(resourceId)
        // Use a buffered reader to efficiently read the text from the input stream
        return inputStream.bufferedReader().use { it.readText() }
    }
}