package com.jetbrains.handson.json

actual fun sendDataToSharedModule(data: String) {
    SharedModule.processData(data)
}