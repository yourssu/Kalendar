package com.yourssu.kalendar

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        alwaysOnTop = true, // 윈도우를 항상 위에 표시(디버그용)
        title = "Kalendar",
    ) {
        App()
    }
}