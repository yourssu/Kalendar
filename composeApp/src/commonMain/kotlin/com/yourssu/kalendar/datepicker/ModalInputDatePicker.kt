package com.yourssu.kalendar.datepicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate

/**
 *
 * @param selectedDateText 입력 필드에 표시될 날짜 텍스트
 * @param onTextFieldClick 입력 필드 클릭 시
 * @param showDatePicker 다이얼로그 노출 여부
 * @param onDismissRequest 다이얼로그 닫기 요청
 * @param onConfirmButtonClick 확인 버튼 클릭
 * @param selectedDate 선택된 날짜
 * @param currentMonthDate 현재 달력 페이지의 날짜
 * @param onDateSelected 날짜 선택 시
 * @param onMonthChanged 달 변경 시
 */
@Composable
fun StatelessCustomModalInputDatePicker(
    selectedDateText: String,
    onTextFieldClick: () -> Unit,
    showDatePicker: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    selectedDate: LocalDate?,
    currentMonthDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onMonthChanged: (LocalDate) -> Unit
) {
    // 입력 필드 UI
    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        OutlinedTextField(
            value = selectedDateText,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onTextFieldClick),
            label = { Text("날짜 선택") }
        )
    }

    // 날짜 선택 다이얼로그
    StatelessCustomModalDatePicker(
        show = showDatePicker,
        onDismissRequest = onDismissRequest,
        onConfirmButtonClick = onConfirmButtonClick,
        selectedDate = selectedDate,
        currentMonthDate = currentMonthDate,
        onDateSelected = onDateSelected,
        onMonthChanged = onMonthChanged
    )
}