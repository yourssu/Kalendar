package com.yourssu.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yourssu.shared.date.Date
import com.yourssu.shared.date.DayOfWeek
import com.yourssu.shared.state.KalendarState

/**
 * shared 모듈의 commonMain에 포함된 예시 컴포저블 함수입니다.
 */
@Composable
fun SharedExampleComposable(name: String, modifier: Modifier = Modifier, currentTimeSecond: Long) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column {
            val utcDate = Date() // UTC
            val nowDate = Date(currentTimeSecond)  // 기기에게 전달받은 시간

            var kalendarState by remember {
                mutableStateOf(
                    KalendarState(
                        nowDate,
                        DayOfWeek.SUNDAY,
                        listOf()
                    )
                )
            }

            Text(text = "안녕, $name! 여기는 shared 모듈입니다.")

            /* Date.kt 테스트 */
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "=== Date.kt 테스트 ==="
            )

            // 월 이동 버튼
            Column {
                Button(
                    onClick = {
                        val c = kalendarState.currentDate
                        val newDate = Date(year = c.year, month = c.month - 1, day = c.day)
                        kalendarState = kalendarState.copy(newDate, kalendarState.startOfWeek, kalendarState.selectedDates)
                    }
                ) { Text("이전 달") }

                Button(
                    onClick = {
                        val c = kalendarState.currentDate
                        val newDate = Date(year = c.year, month = c.month + 1, day = c.day)
                        kalendarState = kalendarState.copy(newDate, kalendarState.startOfWeek, kalendarState.selectedDates)
                    }
                ) { Text("다음 달") }
            }
            MonthlyKalendar(
                kalendarState = kalendarState,
                onDateClick = { date ->
                    val exists = kalendarState.selectedDates.any {
                        it.simpleCalendarFormat() == date.simpleCalendarFormat()
                    }
                    kalendarState = if (!exists) {
                        kalendarState.copy(selectedDates = kalendarState.selectedDates + date)
                    } else {
                        kalendarState.copy(selectedDates = kalendarState.selectedDates.filterNot {
                            it.simpleCalendarFormat() == date.simpleCalendarFormat()
                        })
                    }
                },
                headerStyle = HeaderStyle.default(),                         // 헤더: 베이식 타이틀에 modifier만 적용
                weekDayStyle = WeekDayStyle.default(),                       // 요일: 베이식 레이블에 modifier만 적용
                dateCellStyle = DateCellStyle.default(),                     // 날짜 셀: 베이식 셀에 modifier만 적용
                colors = CalendarColors.default().copy(                      // 선택 배경만 예시로 변경
                    selectedDayBackgroundColor = Color.LightGray
                )
            )

            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "선택한 날 : ${
                    kalendarState.selectedDates.joinToString(",") { it.simpleCalendarFormat() }
                }"
            )

            Text("- UTC -")
            Text(utcDate.simpleCalendarFormat())
            Text(utcDate.simpleTimeFormat())
            Text("- 기기 -")
            Text(nowDate.simpleCalendarFormat())
            Text(nowDate.simpleTimeFormat())

            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "${utcDate.year}년 ${utcDate.month}월"
            )
        }
    }
}

@Composable
fun BasicCalendarTitle(year: Int, month: Int, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        text = "${year}년 ${month}월",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onSurface
    )
}

@Composable
fun BasicWeekLabel(modifier: Modifier, weekName: String) {
    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        text = weekName,
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onSurface
    )
}

@Composable
fun BasicDateCell(modifier: Modifier = Modifier, dateNum: String) {
    Text(
        modifier = modifier, // 상위에서 수직/수평 정렬 포함한 modifier를 전달
        textAlign = TextAlign.Center,
        text = dateNum,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
    )
}