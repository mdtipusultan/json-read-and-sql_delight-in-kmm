package com.jetbrains.handson.json.service

import com.jetbrains.handson.json.DatabaseDriverFactory
import com.jetbrains.handson.json.api.AccountBalance
import com.jetbrains.handson.json.api.AccountTransaction

class MockService(private val databaseDriverFactory: DatabaseDriverFactory) {
    fun loadAccountTransaction(transactions: List<AccountTransaction>) {
        val repo = AccountFactory.getRepository(databaseDriverFactory)
        repo.createAccountTransaction(transactions)
    }
    fun loadAccountBalance(balances: List<AccountBalance>) {
        val repo = AccountFactory.getRepository(databaseDriverFactory)
        repo.createAccountBalance(balances)
    }

}