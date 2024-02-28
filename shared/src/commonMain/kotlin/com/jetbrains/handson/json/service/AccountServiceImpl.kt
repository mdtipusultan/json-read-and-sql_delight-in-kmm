package com.jetbrains.handson.json.service

// Importing necessary classes from other packages or files
import com.jetbrains.handson.json.DatabaseDriverFactory
import com.jetbrains.handson.json.api.AccountBalance
import com.jetbrains.handson.json.api.AccountInstrument
import com.jetbrains.handson.json.api.AccountReceivable
import com.jetbrains.handson.json.api.AccountService
import com.jetbrains.handson.json.api.AccountTransaction

// Implementation of the AccountService interface
class AccountServiceImpl(private val databaseDriverFactory: DatabaseDriverFactory) : AccountService {
    // Function to get account balances
    override fun getBalanceServices(): List<AccountBalance> {
<<<<<<< HEAD
        val repository = AccountFactory.getRepository(databaseDriverFactory);

=======
        // Retrieving the account repository from the factory
        val repository = AccountFactory.getRepository(databaseDriverFactory)
        // Returning the list of account balances from the repository
>>>>>>> ee17242d235c4117494440d0ecfb5386b5ca593a
        return repository.getAccountBalance()
    }

    // Function to get account instruments
    override fun getInstrumentServices(): List<AccountInstrument> {
        // Retrieving the account repository from the factory
        val repository = AccountFactory.getRepository(databaseDriverFactory)
        // Returning the list of account instruments from the repository
        return repository.getAccountInstrument()
    }

    // Function to get account receivables
    override fun getReceivableServices(): List<AccountReceivable> {
        // Retrieving the account repository from the factory
        val repository = AccountFactory.getRepository(databaseDriverFactory)
        // Returning the list of account receivables from the repository
        return repository.getAccountReceivable()
    }

    // Function to get account transactions
    override fun getTransactionServices(): List<AccountTransaction> {
        // Retrieving the account repository from the factory
        val repository = AccountFactory.getRepository(databaseDriverFactory)
        // Returning the list of account transactions from the repository
        return repository.getAccountTransaction()
    }
}
