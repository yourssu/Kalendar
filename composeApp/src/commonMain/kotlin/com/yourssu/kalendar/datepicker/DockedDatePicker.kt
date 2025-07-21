package com.yourssu.kalendar.datepicker

import CalendarGrid
import CustomDatePickerHeader
import DayOfWeekHeader
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

/**
 *
 * @param selectedDate 외부에서 관리하는 선택된 날짜
 * @param currentMonthDate 외부에서 관리하는 현재 달력 페이지의 날짜 (보통 1일)
 * @param onDateSelected 날짜 선택 시 호출될 람다
 * @param onMonthChanged 달 변경 시(이전/다음) 호출될 람다
 */
@Composable
fun StatelessCustomDockedDatePicker(
    selectedDate: LocalDate?,
    currentMonthDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onMonthChanged: (LocalDate) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        CustomDatePickerHeader(
            currentMonthDate = currentMonthDate,
            onPreviousMonth = { onMonthChanged(currentMonthDate.minus(1, DateTimeUnit.MONTH)) },
            onNextMonth = { onMonthChanged(currentMonthDate.plus(1, DateTimeUnit.MONTH)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DayOfWeekHeader()
        Spacer(modifier = Modifier.height(8.dp))
        CalendarGrid(
            currentMonthDate = currentMonthDate,
            selectedDate = selectedDate,
            onDateSelected = onDateSelected
        )
    }
}

