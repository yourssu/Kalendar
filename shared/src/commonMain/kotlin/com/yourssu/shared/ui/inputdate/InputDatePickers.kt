package com.yourssu.shared.ui.inputdate

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp

/* -------------------- 날짜 유틸 (표준 라이브러리만 사용) -------------------- */

data class DateParts(val year: Int, val month: Int, val day: Int)

private fun isLeap(y: Int) = (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)

private fun daysInMonth(y: Int, m: Int): Int = when (m) {
    1,3,5,7,8,10,12 -> 31
    4,6,9,11 -> 30
    2 -> if (isLeap(y)) 29 else 28
    else -> 0
}

private fun isValidDate(y: Int, m: Int, d: Int): Boolean =
    m in 1..12 && d in 1..daysInMonth(y, m)

private fun parseDigitsToDateOrNull(digits: String): DateParts? {
    if (digits.length != 8) return null
    val m = digits.substring(0, 2).toIntOrNull() ?: return null
    val d = digits.substring(2, 4).toIntOrNull() ?: return null
    val y = digits.substring(4, 8).toIntOrNull() ?: return null
    return if (isValidDate(y, m, d)) DateParts(y, m, d) else null
}

private fun dotFormat(dp: DateParts): String {
    val mm = dp.month.toString().padStart(2, '0')
    val dd = dp.day.toString().padStart(2, '0')
    return "${dp.year}.$mm.$dd"
}

private fun compare(a: DateParts, b: DateParts): Int =
    compareValuesBy(a, b, DateParts::year, DateParts::month, DateParts::day)

/* -------------------- 커서 튐 방지용 VisualTransformation -------------------- */
/* 내부 상태는 "숫자만"(예: 20220216) 보관, 화면에는 mm/dd/yyyy로 표시 */
private object MmDdYyyyTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(8)
        val out = buildString {
            for (i in digits.indices) {
                append(digits[i])
                if (i == 1 || i == 3) append('/')
            }
        }
        val mapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = when {
                offset <= 2 -> offset
                offset <= 4 -> offset + 1
                else -> offset + 2
            }.coerceAtMost(out.length)

            override fun transformedToOriginal(offset: Int): Int = when {
                offset <= 2 -> offset
                offset <= 5 -> (offset - 1).coerceAtLeast(0)
                else -> (offset - 2).coerceAtLeast(0)
            }.coerceAtMost(digits.length)
        }
        return TransformedText(AnnotatedString(out), mapping)
    }
}

/* -------------------- 단일 날짜 인풋 피커 (Material) -------------------- */

@Composable
fun InputDatePickerSingle(
    modifier: Modifier = Modifier,
    initialText: String = "",
    onCancel: () -> Unit,
    onConfirm: (DateParts) -> Unit
) {
    var digits by remember { mutableStateOf(initialText.filter { it.isDigit() }.take(8)) }
    val parsed = remember(digits) { parseDigitsToDateOrNull(digits) }
    val showError = remember(digits) { digits.length == 8 && parsed == null }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Select date", style = MaterialTheme.typography.subtitle2)
        Spacer(Modifier.height(6.dp))

        val header = parsed?.let { dotFormat(it) } ?: "Enter date"
        Text(header, style = MaterialTheme.typography.h1)

        Divider(Modifier.padding(top = 8.dp, bottom = 12.dp))

        OutlinedTextField(
            value = digits,
            onValueChange = { new -> digits = new.filter { it.isDigit() }.take(8) },
            label = { Text("Date") },
            placeholder = { Text("mm/dd/yyyy") },
            visualTransformation = MmDdYyyyTransformation,
            singleLine = true,
            isError = showError,
            modifier = Modifier.fillMaxWidth()
        )

        if (showError) {
            Spacer(Modifier.height(6.dp))
            Text(
                "Enter a valid date (mm/dd/yyyy)",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2
            )
        }

        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth()) {
            TextButton(onClick = onCancel) { Text("Cancel") }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { parsed?.let(onConfirm) },
                enabled = parsed != null
            ) { Text("OK") }
        }
    }
}

/* -------------------- 날짜 범위 인풋 피커 (Material) -------------------- */

@Composable
fun InputDatePickerRange(
    modifier: Modifier = Modifier,
    initialStartText: String = "",
    initialEndText: String = "",
    onCancel: () -> Unit,
    onConfirm: (start: DateParts, end: DateParts) -> Unit
) {
    var startDigits by remember { mutableStateOf(initialStartText.filter { it.isDigit() }.take(8)) }
    var endDigits by remember { mutableStateOf(initialEndText.filter { it.isDigit() }.take(8)) }

    val startParsed = remember(startDigits) { parseDigitsToDateOrNull(startDigits) }
    val endParsed = remember(endDigits) { parseDigitsToDateOrNull(endDigits) }
    val endBeforeStart = remember(startParsed, endParsed) {
        startParsed != null && endParsed != null && compare(endParsed, startParsed) < 0
    }

    val showStartError = startDigits.length == 8 && startParsed == null
    val showEndError = endDigits.length == 8 && (endParsed == null || endBeforeStart)

    val previewOk = startParsed != null && endParsed != null && !endBeforeStart

    Column(modifier = modifier.padding(16.dp)) {
        Text("Select date", style = MaterialTheme.typography.subtitle2)
        Spacer(Modifier.height(6.dp))

        // ✅ 큰 제목 텍스트를 그대로 범위/제목으로 교체 (크기 유지: h5)
        val header = if (previewOk) {
            "${dotFormat(startParsed!!)} – ${dotFormat(endParsed!!)}"
        } else {
            "Enter dates"
        }
        Text(header, style = MaterialTheme.typography.h5)

        Divider(Modifier.padding(top = 8.dp, bottom = 12.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(
                value = startDigits,
                onValueChange = { startDigits = it.filter { ch -> ch.isDigit() }.take(8) },
                label = { Text("Date") },
                placeholder = { Text("mm/dd/yyyy") },
                visualTransformation = MmDdYyyyTransformation,
                singleLine = true,
                isError = showStartError,
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = endDigits,
                onValueChange = { endDigits = it.filter { ch -> ch.isDigit() }.take(8) },
                label = { Text("End date") },
                placeholder = { Text("mm/dd/yyyy") },
                visualTransformation = MmDdYyyyTransformation,
                singleLine = true,
                isError = showEndError,
                modifier = Modifier.weight(1f)
            )
        }

        if (showStartError || showEndError) {
            Spacer(Modifier.height(6.dp))
            Text(
                text = when {
                    showStartError -> "Invalid start date"
                    endParsed == null -> "Invalid end date"
                    endBeforeStart -> "End date must be after start date"
                    else -> ""
                },
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2
            )
        }

        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth()) {
            TextButton(onClick = onCancel) { Text("Cancel") }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { onConfirm(startParsed!!, endParsed!!) },
                enabled = previewOk
            ) { Text("OK") }
        }
    }
}