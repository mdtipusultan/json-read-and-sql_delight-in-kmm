package com.jetbrains.handson.json.android

import com.jetbrains.handson.json.SharedModule

// AndroidModule.kt (Android-specific module)


fun sendDataToSharedModule(data: String) {
    SharedModule.processData(data)
}
