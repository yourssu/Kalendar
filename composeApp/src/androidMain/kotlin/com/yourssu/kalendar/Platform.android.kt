package com.yourssu.kalendar

import android.os.Build
import java.util.TimeZone

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val seconds: Long = (System.currentTimeMillis() + TimeZone.getDefault().getOffset(System.currentTimeMillis())) / 1000
}

actual fun getPlatform(): Platform = AndroidPlatform()