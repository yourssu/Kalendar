package com.yourssu.kalendar.timepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InputTimePicker(
    hour: Int,
    minute: Int,
    isAm: Boolean,
    isEditingHour: Boolean,
    isEditingMinute: Boolean,
    hourText: String,
    minuteText: String,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onAmPmChange: (Boolean) -> Unit,
    onSelectionChange: (Boolean) -> Unit,
    onEditingHourChange: (Boolean) -> Unit,
    onEditingMinuteChange: (Boolean) -> Unit,
    onHourTextChange: (String) -> Unit,
    onMinuteTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isSelectingHour: Boolean = true,
) {
    Card(
        modifier = modifier.width(320.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        TimeDisplayHeader(
            hour = hour,
            minute = minute,
            isAm = isAm,
            isSelectingHour = isSelectingHour,
            isEditingHour = isEditingHour,
            isEditingMinute = isEditingMinute,
            hourText = hourText,
            minuteText = minuteText,
            onHourChange = onHourChange,
            onMinuteChange = onMinuteChange,
            onSelectionChange = onSelectionChange,
            onAmPmChange = onAmPmChange,
            onEditingHourChange = onEditingHourChange,
            onEditingMinuteChange = onEditingMinuteChange,
            onHourTextChange = onHourTextChange,
            onMinuteTextChange = onMinuteTextChange
        )
    }
}

@Composable
private fun TimeDisplayHeader(
    hour: Int,
    minute: Int,
    isAm: Boolean,
    isSelectingHour: Boolean,
    isEditingHour: Boolean,
    isEditingMinute: Boolean,
    hourText: String,
    minuteText: String,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onSelectionChange: (Boolean) -> Unit,
    onAmPmChange: (Boolean) -> Unit,
    onEditingHourChange: (Boolean) -> Unit,
    onEditingMinuteChange: (Boolean) -> Unit,
    onHourTextChange: (String) -> Unit,
    onMinuteTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.width(72.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isEditingHour) {
                    TimeInputField(
                        value = hourText,
                        onValueChange = { newValue ->
                            if (newValue.length <= 2 && newValue.all { it.isDigit() }) {
                                onHourTextChange(newValue)
                            }
                        },
                        onDone = {
                            val newHour = hourText.toIntOrNull()?.coerceIn(1, 12) ?: hour
                            onHourChange(newHour)
                            onEditingHourChange(false)
                        },
                        onFocusLost = {
                            val newHour = hourText.toIntOrNull()?.coerceIn(1, 12) ?: hour
                            onHourChange(newHour)
                            onEditingHourChange(false)
                        },
                        isSelected = isSelectingHour
                    )
                } else {
                    TextButton(
                        onClick = {
                            onSelectionChange(true)
                            onEditingHourChange(true)
                        },
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
                }
            }

            Text(
                text = ":",
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Box(
                modifier = Modifier.width(72.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isEditingMinute) {
                    TimeInputField(
                        value = minuteText,
                        onValueChange = { newValue ->
                            if (newValue.length <= 2 && newValue.all { it.isDigit() }) {
                                onMinuteTextChange(newValue)
                            }
                        },
                        onDone = {
                            val newMinute = minuteText.toIntOrNull()?.coerceIn(0, 59) ?: minute
                            onMinuteChange(newMinute)
                            onEditingMinuteChange(false)
                        },
                        onFocusLost = {
                            val newMinute = minuteText.toIntOrNull()?.coerceIn(0, 59) ?: minute
                            onMinuteChange(newMinute)
                            onEditingMinuteChange(false)
                        },
                        isSelected = !isSelectingHour
                    )
                } else {
                    TextButton(
                        onClick = {
                            onSelectionChange(false)
                            onEditingMinuteChange(true)
                        },
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
private fun TimeInputField(
    value: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    onFocusLost: () -> Unit,
    isSelected: Boolean
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(72.dp)
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                if (!focusState.isFocused) {
                    onFocusLost()
                }
            },
        textStyle = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.Normal,
            color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            },
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
private fun EditHourInputTimePickerPreview() {
    var hour by remember { mutableStateOf(12) }
    var minute by remember { mutableStateOf(30) }
    var isAm by remember { mutableStateOf(true) }
    var isSelectingHour by remember { mutableStateOf(true) }
    var isEditingHour by remember { mutableStateOf(true) }
    var isEditingMinute by remember { mutableStateOf(false) }
    var hourText by remember { mutableStateOf("12") }
    var minuteText by remember { mutableStateOf("30") }


    InputTimePicker(
        hour = hour,
        minute = minute,
        isAm = isAm,
        isEditingHour = isEditingHour,
        isEditingMinute = isEditingMinute,
        hourText = hourText,
        minuteText = minuteText,
        onHourChange = {
            hour = it
            if (!isEditingHour) {
                hourText = it.toString().padStart(2, '0')
            }
        },
        onMinuteChange = {
            minute = it
            if (!isEditingMinute) {
                minuteText = it.toString().padStart(2, '0')
            }
        },
        onAmPmChange = { isAm = it },
        onSelectionChange = { isSelectingHour = it },
        onEditingHourChange = { isEditingHour = it },
        onEditingMinuteChange = { isEditingMinute = it },
        onHourTextChange = { hourText = it },
        onMinuteTextChange = { minuteText = it },
        isSelectingHour = isSelectingHour
    )
}

@Preview
@Composable
private fun EditMinuteInputTimePickerPreview() {
    var hour by remember { mutableStateOf(12) }
    var minute by remember { mutableStateOf(30) }
    var isAm by remember { mutableStateOf(true) }
    var isSelectingHour by remember { mutableStateOf(false) }
    var isEditingHour by remember { mutableStateOf(false) }
    var isEditingMinute by remember { mutableStateOf(true) }
    var hourText by remember { mutableStateOf("12") }
    var minuteText by remember { mutableStateOf("30") }


    InputTimePicker(
        hour = hour,
        minute = minute,
        isAm = isAm,
        isEditingHour = isEditingHour,
        isEditingMinute = isEditingMinute,
        hourText = hourText,
        minuteText = minuteText,
        onHourChange = {
            hour = it
            if (!isEditingHour) {
                hourText = it.toString().padStart(2, '0')
            }
        },
        onMinuteChange = {
            minute = it
            if (!isEditingMinute) {
                minuteText = it.toString().padStart(2, '0')
            }
        },
        onAmPmChange = { isAm = it },
        onSelectionChange = { isSelectingHour = it },
        onEditingHourChange = { isEditingHour = it },
        onEditingMinuteChange = { isEditingMinute = it },
        onHourTextChange = { hourText = it },
        onMinuteTextChange = { minuteText = it },
        isSelectingHour = isSelectingHour
    )
}