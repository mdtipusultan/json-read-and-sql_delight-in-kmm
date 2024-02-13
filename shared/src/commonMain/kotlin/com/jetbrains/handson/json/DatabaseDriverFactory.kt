package com.jetbrains.handson.json

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory{
    fun createDriver(): SqlDriver
}
