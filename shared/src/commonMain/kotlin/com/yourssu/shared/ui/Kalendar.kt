package com.yourssu.shared.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yourssu.shared.date.Date
import com.yourssu.shared.state.KalendarState

@Composable
fun MonthlyKalendar(
    kalendarState: KalendarState,
    onDateClick: (Date) -> Unit, // 날짜 클릭 이벤트 콜백
    modifier: Modifier = Modifier, // ... 기타 커스텀 파라미터
    titleUI: @Composable (Int, Int) -> Unit,
    weekLabelUI: @Composable (String) -> Unit,
    dateCellUI: @Composable (Modifier, String, Boolean) -> Unit, // API 이용자는 직접 UI를 만들면서 클릭될 요소를 정할 수 있습니다
) {

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
        ) {
            titleUI(
                kalendarState.currentDate.year,
                kalendarState.currentDate.month,
            )
        }

        val calArrays = Date.getMonthCalendar(
            kalendarState.currentDate.year,
            kalendarState.currentDate.month,
            kalendarState.startOfWeek
        )

        Row {
            for (cell in Date.getWeekDays(kalendarState.startOfWeek).map { it.kor }) {
                weekLabelUI(cell)
//                Text(modifier = Modifier.padding(top = 12.dp).weight(1f), text = cell)
            }
        }
        for (calArray in calArrays) {
            Row {
                for (cell in calArray) {
                    val currentCell = cell?.toString() ?: ""
                    dateCellUI(
                        Modifier.clickable {
                            if (currentCell.isNotBlank()) {
                                onDateClick(
                                    Date(
                                        kalendarState.currentDate.year,
                                        kalendarState.currentDate.month,
                                        currentCell.toInt()
                                    )
                                )
                            }
                        },
                        currentCell,
                        currentCell.isNotBlank() && kalendarState.selectedDates.any {
                            it.simpleCalendarFormat() == Date(
                                kalendarState.currentDate.year,
                                kalendarState.currentDate.month,
                                currentCell.toInt()
                            ).simpleCalendarFormat()
                        })

                }
            }
        }
    }
}

@Composable
fun WeeklyKalendar(
    kalendarState: KalendarState,
    onDateClick: (Date) -> Unit, // 날짜 클릭 이벤트 콜백
    modifier: Modifier = Modifier // ... 기타 커스텀 파라미터
) {

}