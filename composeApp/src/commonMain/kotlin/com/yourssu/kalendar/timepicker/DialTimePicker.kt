package com.yourssu.kalendar.timepicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialTimePicker(
    hour: Int,
    minute: Int,
    isAm: Boolean,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onAmPmChange: (Boolean) -> Unit,
    onSelectionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isSelectingHour: Boolean = true,
) {
    Card(
        modifier = modifier.width(320.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 시간 표시 헤더
            TimeDisplayHeader(
                hour = hour,
                minute = minute,
                isAm = isAm,
                isSelectingHour = isSelectingHour,
                onSelectionChange = onSelectionChange,
                onAmPmChange = onAmPmChange
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 다이얼 타임피커
            Box(
                modifier = Modifier.size(280.dp),
                contentAlignment = Alignment.Center
            ) {
                DialTimePicker(
                    hour = hour,
                    minute = minute,
                    isSelectingHour = isSelectingHour,
                    onHourChange = onHourChange,
                    onMinuteChange = onMinuteChange
                )
            }
        }
    }
}

@Composable
private fun TimeDisplayHeader(
    hour: Int,
    minute: Int,
    isAm: Boolean,
    isSelectingHour: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    onAmPmChange: (Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 시간:분 표시
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // 시간 표시
            TextButton(
                onClick = { onSelectionChange(true) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (isSelectingHour) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    }
                )
            ) {
                Text(
                    text = if (hour < 10) "0$hour" else "$hour",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Text(
                text = ":",
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            // 분 표시
            TextButton(
                onClick = { onSelectionChange(false) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (!isSelectingHour) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    }
                )
            ) {
                Text(
                    text = if (minute < 10) "0$minute" else "$minute",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        // AM/PM 토글
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = isAm,
                onClick = { onAmPmChange(true) },
                label = { Text("AM") }
            )
            FilterChip(
                selected = !isAm,
                onClick = { onAmPmChange(false) },
                label = { Text("PM") }
            )
        }
    }
}

@Composable
private fun DialTimePicker(
    hour: Int,
    minute: Int,
    isSelectingHour: Boolean,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .size(280.dp)
            .pointerInput(isSelectingHour) {
                detectDragGestures { change, _ ->
                    val center = Offset(size.width / 2f, size.height / 2f)
                    val angle = atan2(
                        change.position.y - center.y,
                        change.position.x - center.x
                    )
                    val adjustedAngle = (angle + PI / 2 + 2 * PI) % (2 * PI)

                    if (isSelectingHour) {
                        val hourValue = ((adjustedAngle / (2 * PI) * 12).roundToInt() % 12)
                        val newHour = if (hourValue == 0) 12 else hourValue
                        onHourChange(newHour)
                    } else {
                        val minute = ((adjustedAngle / (2 * PI) * 60).roundToInt() % 60)
                        onMinuteChange(minute)
                    }
                }
            }
    ) {
        drawDial(hour, minute, isSelectingHour, textMeasurer)
    }
}

private fun DrawScope.drawDial(
    hour: Int,
    minute: Int,
    isSelectingHour: Boolean,
    textMeasurer: TextMeasurer
) {
    val center = size.center
    val radius = size.minDimension / 2f
    val outerRadius = radius * 0.9f

    drawCircle(
        color = Color.Gray.copy(alpha = 0.1f),
        radius = outerRadius,
        center = center
    )

    if (isSelectingHour) {
        drawHourDial(center, outerRadius, hour, textMeasurer)
    } else {
        drawMinuteDial(center, outerRadius, minute, textMeasurer)
    }
}

private fun DrawScope.drawHourDial(
    center: Offset,
    outerRadius: Float,
    selectedHour: Int,
    textMeasurer: TextMeasurer
) {
    // 시간 숫자들 그리기 (1-12)
    for (i in 1..12) {
        val angle = (i * 30 - 90) * PI / 180
        val x = center.x + outerRadius * 0.8f * cos(angle).toFloat()
        val y = center.y + outerRadius * 0.8f * sin(angle).toFloat()

        val isSelected = i == selectedHour

        // 선택된 시간에 배경 원 그리기
        if (isSelected) {
            drawCircle(
                color = Color.Magenta,
                radius = 30f,
                center = Offset(x, y)
            )
        }

        // 시간 숫자 그리기
        val textLayoutResult = textMeasurer.measure(
            text = i.toString(),
            style = TextStyle(
                color = if (isSelected) Color.White else Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        )

        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(
                x - textLayoutResult.size.width / 2f,
                y - textLayoutResult.size.height / 2f
            )
        )
    }

    // 선택된 시간으로 선 그리
    val selectedAngle = (selectedHour * 30 - 90) * PI / 180
    val lineEnd = Offset(
        center.x + outerRadius * 0.8f * cos(selectedAngle).toFloat(),
        center.y + outerRadius * 0.8f * sin(selectedAngle).toFloat()
    )

    drawLine(
        color = Color.Magenta,
        start = center,
        end = lineEnd,
        strokeWidth = 4f
    )
}

private fun DrawScope.drawMinuteDial(
    center: Offset,
    outerRadius: Float,
    selectedMinute: Int,
    textMeasurer: TextMeasurer
) {
    // 분 표시 (5분 단위)
    for (i in 0..11) {
        val minute = i * 5
        val angle = (minute * 6 - 90) * PI / 180
        val x = center.x + outerRadius * 0.8f * cos(angle).toFloat()
        val y = center.y + outerRadius * 0.8f * sin(angle).toFloat()

        val isSelected = minute == selectedMinute

        // 분 배경 원 그리기
        drawCircle(
            color = if (isSelected) Color.Magenta else Color.Gray.copy(alpha = 0.3f),
            radius = if (isSelected) 30f else 12f,
            center = Offset(x, y)
        )

        // 분 숫자 그리기
        val displayMinute = if (minute == 0) "00" else minute.toString().padStart(2, '0')
        val textLayoutResult = textMeasurer.measure(
            text = displayMinute,
            style = TextStyle(
                color = if (isSelected) Color.White else Color.Black,
                fontSize = if (isSelected) 16.sp else 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        )

        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(
                x - textLayoutResult.size.width / 2f,
                y - textLayoutResult.size.height / 2f
            )
        )
    }

    // 선택된 분으로 선 그리기
    val selectedAngle = (selectedMinute * 6 - 90) * PI / 180
    val lineEnd = Offset(
        center.x + outerRadius * 0.8f * cos(selectedAngle).toFloat(),
        center.y + outerRadius * 0.8f * sin(selectedAngle).toFloat()
    )

    drawLine(
        color = Color.Magenta,
        start = center,
        end = lineEnd,
        strokeWidth = 4f
    )
}

@Composable
@Preview
fun TimePickerExample() {
    var hour by remember { mutableIntStateOf(9) }
    var minute by remember { mutableIntStateOf(30) }
    var isAm by remember { mutableStateOf(true) }
    var isSelectingHour by remember { mutableStateOf(true) }

    DialTimePicker(
        hour = hour,
        minute = minute,
        isAm = isAm,
        isSelectingHour = isSelectingHour,
        onHourChange = { newHour -> hour = newHour },
        onMinuteChange = { newMinute -> minute = newMinute },
        onAmPmChange = { newIsAm -> isAm = newIsAm },
        onSelectionChange = { selectingHour -> isSelectingHour = selectingHour }
    )
}

@Composable
@Preview
fun TimePickerNumExample() {
    var hour by remember { mutableIntStateOf(9) }
    var minute by remember { mutableIntStateOf(15) }
    var isAm by remember { mutableStateOf(false) }
    var isSelectingHour by remember { mutableStateOf(false) }

    DialTimePicker(
        hour = hour,
        minute = minute,
        isAm = isAm,
        isSelectingHour = isSelectingHour,
        onHourChange = { newHour -> hour = newHour },
        onMinuteChange = { newMinute -> minute = newMinute },
        onAmPmChange = { newIsAm -> isAm = newIsAm },
        onSelectionChange = { selectingHour -> isSelectingHour = selectingHour }
    )
}