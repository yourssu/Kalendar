package com.yourssu.kalendar

import platform.Foundation.NSDate
import platform.Foundation.NSTimeZone
import platform.Foundation.secondsFromGMT
import platform.Foundation.systemTimeZone
import platform.Foundation.timeIntervalSince1970
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val seconds: Long = NSDate().timeIntervalSince1970.toLong() + NSTimeZone.systemTimeZone.secondsFromGMT
}

actual fun getPlatform(): Platform = IOSPlatform()