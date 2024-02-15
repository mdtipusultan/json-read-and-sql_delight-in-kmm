package com.jetbrains.handson.json.repository

// Importing necessary classes from other packages or files
import com.jetbrains.handson.json.CapitaDb
import com.jetbrains.handson.json.DatabaseDriverFactory
import com.jetbrains.handson.json.api.AccountBalance
import com.jetbrains.handson.json.api.AccountInstrument
import com.jetbrains.handson.json.api.AccountReceivable
import com.jetbrains.handson.json.api.AccountRepository
import com.jetbrains.handson.json.api.AccountTransaction

// Internal implementation of the AccountRepository interface
internal class AccountLocalRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) : AccountRepository {
    // Initializing database using CapitaDb and the provided database driver
    private val database = CapitaDb(databaseDriverFactory.createDriver())

    // Implementation of the getAccountBalance function
    override fun getAccountBalance(): List<AccountBalance> {
        return database.accountBalanceQueries.getAccountBalance()
            .executeAsList()
            .map { accountBalanceData ->
                AccountBalance(
                    // Mapping retrieved data to AccountBalance objects
                    accountCode = accountBalanceData?.accountCode!!,
                    accruedCharge = accountBalanceData?.accruedCharge!!,
                    assetValue = accountBalanceData?.assetValue!!,
                    buyingPower = accountBalanceData?.buyingPower!!,
                    cashBalance = accountBalanceData?.cashBalance!!,
                    costValue = accountBalanceData?.costValue!!,
                    currentBalance = accountBalanceData?.currentBalance!!,
                    deptEquityRatio = accountBalanceData?.deptEquityRatio!!,
                    equity = accountBalanceData?.equity!!,
                    equityDebtRatio = accountBalanceData?.equityDebtRatio!!,
                    immatureBalance = accountBalanceData?.immatureBalance!!,
                    loanRatio = accountBalanceData?.loanRatio!!,
                    marginEquity = accountBalanceData?.marginEquity!!,
                    marketValue = accountBalanceData?.marketValue!!,
                    totalDeposit = accountBalanceData?.totalDeposit!!,
                    totalWithdrawal = accountBalanceData?.totalWithdrawal!!,
                    unclearCheque = accountBalanceData?.unclearCheque!!,
                )
            }
    }

    // Implementation of the getAccountInstrument function
    override fun getAccountInstrument(): List<AccountInstrument> {
        return database.accountInstrumentQueries.getAccountInstrumentData()
            .executeAsList()
            .map { accountInstrument ->
                AccountInstrument(
                    // Mapping retrieved data to AccountInstrument objects
                    instrumentIndex = accountInstrument?.instrumentIndex!!,
                    longName = accountInstrument?.long_name!!,
                    shortName = accountInstrument?.short_name!!,
                    value = accountInstrument?.value_!!,
                    closedPrice = accountInstrument?.closed_price!!,
                    change = accountInstrument?.change!!,
                    changeIcon = accountInstrument?.change_icon!!,
                    totalQuantity = accountInstrument?.total_quantity!!,
                    salableQuantity = accountInstrument?.salable_quantity!!,
                    averageCost = accountInstrument?.average_cost!!,
                    totalCost = accountInstrument?.total_cost!!,
                    closePrice = accountInstrument?.average_cost!!,
                    unrealizedGain = accountInstrument?.unrealized_gain!!,
                    gainPercent = accountInstrument?.gain_percent!!,
                    costValue = accountInstrument?.cost_value!!,
                )
            }
    }

    // Implementation of the getAccountReceivable function
    override fun getAccountReceivable(): List<AccountReceivable> {
        return database.accountReceivableQueries.getAccountReceivableData()
            .executeAsList()
            .map { accountReceivableData ->
                AccountReceivable(
                    // Mapping retrieved data to AccountReceivable objects
                    name = accountReceivableData?.name!!,
                    company1 = accountReceivableData?.company1!!,
                    company2 = accountReceivableData?.company2!!,
                    shareQuantity1 = accountReceivableData?.shareQuantity1!!,
                    shareQuantity2 = accountReceivableData?.shareQuantity2!!,
                    amount1 = accountReceivableData?.amount1!!,
                    amount2 = accountReceivableData?.amount2!!,
                )
            }
    }

    // Implementation of the getAccountTransaction function
    override fun getAccountTransaction(): List<AccountTransaction> {
        return database.accountTransactionQueries.getAccountTransactionData()
            .executeAsList()
            .map { accountTransactionData ->
                AccountTransaction(
                    // Mapping retrieved data to AccountTransaction objects
                    transferType = accountTransactionData?.transferType!!,
                    totalAmount = accountTransactionData?.totalAmount!!,
                    description = accountTransactionData?.description!!,
                    quantity = accountTransactionData?.quantity!!,
                    date = accountTransactionData?.date!!,
                    identity = accountTransactionData?.identity!!
                )
            }
    }

    // Implementation of the createAccountTransaction function
    override fun createAccountTransaction(transactions: List<AccountTransaction>) {
        transactions.forEach { transaction ->
            val existingTransaction =
                database.accountTransactionQueries.getAccountTransactionByUniqueId(transaction.transferType)
            if (existingTransaction.executeAsList().isEmpty()) {
                // Inserting new account transaction data into the database
                database.accountTransactionQueries.insertAccountTransactionData(
                    transferType = transaction.transferType,
                    totalAmount = transaction.totalAmount,
                    description = transaction.description,
                    quantity = transaction.quantity,
                    date = transaction.date,
                    identity = transaction.identity
                )
            }
        }
    }

    // Implementation of the createAccountInstrument function
    override fun createAccountInstrument(instruments: List<AccountInstrument>) {
        instruments.forEach { instrument ->
            val existingInstrument = database.accountInstrumentQueries.getAccountInstrumentByUniqueId(instrument.shortName)
            if (existingInstrument.executeAsList().isEmpty()) {
                // Inserting new account instrument data into the database
                database.accountInstrumentQueries.insertAccountInstrumentData(
                    instrumentIndex = instrument.instrumentIndex,
                    long_name = instrument.longName,
                    short_name = instrument.shortName,
                    value_ = instrument.value,
                    closed_price = instrument.closedPrice,
                    change = instrument.change,
                    change_icon = instrument.changeIcon,
                    total_quantity = instrument.totalQuantity,
                    salable_quantity = instrument.salableQuantity,
                    average_cost = instrument.averageCost,
                    total_cost = instrument.totalCost,
                    close_price = instrument.closePrice,
                    unrealized_gain = instrument.unrealizedGain,
                    gain_percent = instrument.gainPercent,
                    cost_value = instrument.costValue
                )
            }
        }
    }

    // Implementation of the createAccountBalance function
    override fun createAccountBalance(balances: List<AccountBalance>) {
        balances.forEach { balance ->
            val existingBalance =
                database.accountBalanceQueries.getAccountBalanceByUniqueId(balance.accountCode)
            if (existingBalance.executeAsList().isEmpty()) {
                // Inserting new account balance data into the database
                database.accountBalanceQueries.insertAccountBalanceData(
                    accountCode = balance.accountCode,
                    accruedCharge = balance.accruedCharge,
                    assetValue = balance.assetValue,
                    buyingPower = balance.buyingPower,
                    cashBalance = balance.cashBalance,
                    costValue = balance.costValue,
                    currentBalance = balance.currentBalance,
                    deptEquityRatio = balance.deptEquityRatio,
                    equity = balance.equity,
                    equityDebtRatio = balance.equityDebtRatio,
                    immatureBalance = balance.immatureBalance,
                    loanRatio = balance.loanRatio,
                    marginEquity = balance.marginEquity,
                    marketValue = balance.marketValue,
                    totalDeposit = balance.totalDeposit,
                    totalWithdrawal = balance.totalWithdrawal,
                    unclearCheque = balance.unclearCheque,
                )
            }
        }
    }

    // Implementation of the createAccountReceivable function
    override fun createAccountReceivable(receivables: List<AccountReceivable>) {
        receivables.forEach { receivable ->
            val existingReceivable =
                database.accountReceivableQueries.getAccountReceivableByUniqueName(receivable.name)
            if (existingReceivable.executeAsList().isEmpty()) {
                // Inserting new account receivable data into the database
                database.accountReceivableQueries.insertAccountReceivableData(
                    name = receivable.name,
                    company1 = receivable.company1,
                    company2 = receivable.company2,
                    shareQuantity1 = receivable.shareQuantity1,
                    shareQuantity2 = receivable.shareQuantity2,
                    amount1 = receivable.amount1,
                    amount2 = receivable.amount2,
                )
            }
        }
    }
}
