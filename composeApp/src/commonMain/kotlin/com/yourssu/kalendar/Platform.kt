package com.yourssu.kalendar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform