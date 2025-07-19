package com.yourssu.kalendar

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun deviceName(): String {
        return platform.name
    }
    fun deviceSecond(): Long {
        return platform.seconds
    }
}