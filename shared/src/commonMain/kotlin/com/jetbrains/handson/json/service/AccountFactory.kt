package com.jetbrains.handson.json.service

import com.jetbrains.handson.json.Database
import com.jetbrains.handson.json.DatabaseDriverFactory
import com.jetbrains.handson.json.api.AccountRepository
import com.jetbrains.handson.json.api.AccountService
import com.jetbrains.handson.json.repository.AccountLocalRepositoryImpl

object AccountFactory {

//    fun getService(): AccountService {
//        return AccountServiceImpl()
//    }

    fun getRepository(databaseDriverFactory:DatabaseDriverFactory): AccountRepository {

            return AccountLocalRepositoryImpl(databaseDriverFactory)

    }
}
