package com.jetbrains.handson.json

// SharedModule.kt (common module)
object SharedModule {
    fun processData(data: String) {
        // Handle the data received from the Android module
        println("Received data: $data")
        // Your processing logic here
    }
}
