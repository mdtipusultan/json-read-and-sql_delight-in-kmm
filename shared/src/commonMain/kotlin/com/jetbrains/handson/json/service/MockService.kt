package com.jetbrains.handson.json.service

// Importing necessary classes from other packages or files
import com.jetbrains.handson.json.DatabaseDriverFactory
import com.jetbrains.handson.json.api.AccountBalance
import com.jetbrains.handson.json.api.AccountTransaction

// Class for loading mock data into the database
class MockService(private val databaseDriverFactory: DatabaseDriverFactory) {
    // Function to load account transactions into the database
    fun loadAccountTransaction(transactions: List<AccountTransaction>) {
        // Retrieving the account repository from the factory
        val repo = AccountFactory.getRepository(databaseDriverFactory)
        // Creating account transactions in the database using the repository
        repo.createAccountTransaction(transactions)
    }

    // Function to load account balances into the database
    fun loadAccountBalance(balances: List<AccountBalance>) {
        // Retrieving the account repository from the factory
        val repo = AccountFactory.getRepository(databaseDriverFactory)
        // Creating account balances in the database using the repository
        repo.createAccountBalance(balances)
    }
}
