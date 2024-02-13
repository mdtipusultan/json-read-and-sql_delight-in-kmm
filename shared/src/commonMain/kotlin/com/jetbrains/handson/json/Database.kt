package com.jetbrains.handson.json


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = CapitaDb(databaseDriverFactory.createDriver())
}