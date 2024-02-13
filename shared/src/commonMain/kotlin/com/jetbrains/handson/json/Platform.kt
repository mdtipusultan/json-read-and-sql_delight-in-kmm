package com.jetbrains.handson.json

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform