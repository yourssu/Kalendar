package com.yourssu.kalendar

interface Platform {
    val name: String
    val seconds: Long
}

expect fun getPlatform(): Platform