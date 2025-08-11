package com.yourssu.kalendar

import java.util.TimeZone

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val seconds: Long = (System.currentTimeMillis() + TimeZone.getDefault().getOffset(System.currentTimeMillis())) / 1000
}

actual fun getPlatform(): Platform = JVMPlatform()