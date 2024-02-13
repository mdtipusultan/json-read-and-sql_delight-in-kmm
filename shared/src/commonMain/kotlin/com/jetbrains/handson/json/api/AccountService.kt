package com.jetbrains.handson.json.api
interface AccountService {
    fun getBalanceServices(): List<AccountBalance>
    fun getInstrumentServices():List<AccountInstrument>
    fun getReceivableServices():List<AccountReceivable>
    fun getTransactionServices():List<AccountTransaction>
}