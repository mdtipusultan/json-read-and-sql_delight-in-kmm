package com.jetbrains.handson.json.service

import com.jetbrains.handson.json.DatabaseDriverFactory
import com.jetbrains.handson.json.api.AccountBalance
import com.jetbrains.handson.json.api.AccountInstrument
import com.jetbrains.handson.json.api.AccountReceivable
import com.jetbrains.handson.json.api.AccountService
import com.jetbrains.handson.json.api.AccountTransaction

class AccountServiceImpl(private val databaseDriverFactory: DatabaseDriverFactory) : AccountService {
    override fun getBalanceServices(): List<AccountBalance> {
        val repository = AccountFactory.getRepository(databaseDriverFactory);
        return repository.getAccountBalance()
    }

    override fun getInstrumentServices(): List<AccountInstrument> {
        val repository= AccountFactory.getRepository(databaseDriverFactory)
        return repository.getAccountInstrument()
    }

    override fun getReceivableServices(): List<AccountReceivable> {
        val repository= AccountFactory.getRepository(databaseDriverFactory)
        return repository.getAccountReceivable()
    }

    override fun getTransactionServices(): List<AccountTransaction> {
        val repository= AccountFactory.getRepository(databaseDriverFactory)
        return repository.getAccountTransaction()
    }


}