package com.yourssu.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * shared 모듈의 commonMain에 포함된 예시 컴포저블 함수입니다.
 */
@Composable
fun SharedExampleComposable(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = "안녕, $name! 여기는 shared 모듈입니다.")
    }
}