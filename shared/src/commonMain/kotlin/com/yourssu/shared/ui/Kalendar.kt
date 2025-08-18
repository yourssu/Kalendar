package com.yourssu.shared.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yourssu.shared.date.Date
import com.yourssu.shared.state.KalendarState

@Composable
fun MonthlyKalendar(
    kalendarState: KalendarState,
    onDateClick: (Date) -> Unit,
    modifier: Modifier = Modifier,
    headerStyle: HeaderStyle = HeaderStyle.default(),
    weekDayStyle: WeekDayStyle = WeekDayStyle.default(),
    dateCellStyle: DateCellStyle = DateCellStyle.default(),
    colors: CalendarColors = CalendarColors.default()
) {
    Column(modifier = modifier) {

        // 1) 헤더 (월/년)
        BasicCalendarTitle(
            year = kalendarState.currentDate.year,
            month = kalendarState.currentDate.month,
            modifier = headerStyle.modifier
        )

        // 2) 요일 레이블
        Row {
            for (dayName in Date.getWeekDays(kalendarState.startOfWeek).map { it.kor }) {
                BasicWeekLabel(
                    modifier = weekDayStyle.modifier.then(Modifier.weight(1f)),
                    weekName = dayName
                )
            }
        }

        // 3) 날짜 셀 그리드
        val calMatrix = Date.getMonthCalendar(
            kalendarState.currentDate.year,
            kalendarState.currentDate.month,
            kalendarState.startOfWeek
        )

        for (week in calMatrix) {
            Row {
                for (cell in week) {
                    val dayString = cell?.toString().orEmpty()
                    if (dayString.isBlank()) {
                        // 빈 칸
                        Spacer(Modifier.weight(1f).aspectRatio(1f))
                        continue
                    }

                    val dateObj = Date(
                        kalendarState.currentDate.year,
                        kalendarState.currentDate.month,
                        dayString.toInt()
                    )

                    val isSelected = kalendarState.selectedDates.any {
                        it.simpleCalendarFormat() == dateObj.simpleCalendarFormat()
                    }

                    val bg = if (isSelected) colors.selectedDayBackgroundColor else Color.Transparent

                    BasicDateCell(
                        modifier = dateCellStyle.modifier
                            .then(Modifier.weight(1f).aspectRatio(1f))
                            .background(bg)
                            .clickable { onDateClick(dateObj) },
                        dateNum = dayString
                    )
                }
            }
        }
    }
}

@Composable
fun WeeklyKalendar(
    kalendarState: KalendarState,
    onDateClick: (Date) -> Unit,
    modifier: Modifier = Modifier,
    weekDayStyle: WeekDayStyle = WeekDayStyle.default(),
    dateCellStyle: DateCellStyle = DateCellStyle.default(),
    colors: CalendarColors = CalendarColors.default()
) {
    // 현재 월에서, 현재 날짜가 포함된 주 행을 찾음
    val monthMatrix = Date.getMonthCalendar(
        kalendarState.currentDate.year,
        kalendarState.currentDate.month,
        kalendarState.startOfWeek
    )
    val weekRow = monthMatrix.firstOrNull { row ->
        row.contains(kalendarState.currentDate.day)
    } ?: monthMatrix.firstOrNull() ?: emptyList()

    Column(modifier = modifier) {

        // 요일 레이블
        Row {
            for (dayName in Date.getWeekDays(kalendarState.startOfWeek).map { it.kor }) {
                BasicWeekLabel(
                    modifier = weekDayStyle.modifier.then(Modifier.weight(1f)),
                    weekName = dayName
                )
            }
        }

        // 해당 주의 날짜 셀
        Row {
            for (cell in weekRow) {
                val dayString = cell?.toString().orEmpty()
                if (dayString.isBlank()) {
                    Spacer(Modifier.weight(1f).aspectRatio(1f))
                    continue
                }

                val dateObj = Date(
                    kalendarState.currentDate.year,
                    kalendarState.currentDate.month,
                    dayString.toInt()
                )
                val isSelected = kalendarState.selectedDates.any {
                    it.simpleCalendarFormat() == dateObj.simpleCalendarFormat()
                }
                val bg = if (isSelected) colors.selectedDayBackgroundColor else Color.Transparent

                BasicDateCell(
                    modifier = dateCellStyle.modifier
                        .then(Modifier.weight(1f).aspectRatio(1f))
                        .background(bg)
                        .clickable { onDateClick(dateObj) },
                    dateNum = dayString
                )
            }
        }
    }
}