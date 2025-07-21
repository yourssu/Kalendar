package com.yourssu.kalendar.datepicker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.datetime.LocalDate

/**
 *
 * @param show 다이얼로그 노출 여부
 * @param onDismissRequest 다이얼로그 닫기 요청
 * @param onConfirmButtonClick 확인 버튼 클릭
 * @param selectedDate 선택된 날짜
 * @param currentMonthDate 현재 달력 페이지의 날짜
 * @param onDateSelected 날짜 선택 시
 * @param onMonthChanged 달 변경 시
 */
@Composable
fun StatelessCustomModalDatePicker(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    selectedDate: LocalDate?,
    currentMonthDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onMonthChanged: (LocalDate) -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                Column {
                    StatelessCustomDockedDatePicker(
                        selectedDate = selectedDate,
                        currentMonthDate = currentMonthDate,
                        onDateSelected = onDateSelected,
                        onMonthChanged = onMonthChanged
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = onDismissRequest) {
                            Text("취소")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = onConfirmButtonClick) {
                            Text("확인")
                        }
                    }
                }
            }
        }
    }
}
