package com.yourssu.shared.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
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
                        listOf<Date>()
                    )
                )
            }

            Text(text = "안녕, $name! 여기는 shared 모듈입니다.")

            /* Date.kt 테스트 */

            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "=== Date.kt 테스트 ==="
            )

            Row {
                Button(
                    onClick = {
                        val currentDate = kalendarState.currentDate
                        val newDate = Date(
                            year = currentDate.year,
                            month = currentDate.month - 1,  // 이전 달
                            day = currentDate.day
                        )

                        kalendarState = kalendarState.copy(
                            newDate,
                            kalendarState.startOfWeek,
                            kalendarState.selectedDates
                        )
                    }
                ) {
                    Text("이전 달")
                }
                Button(
                    onClick = {
                        val currentDate = kalendarState.currentDate
                        val newDate = Date(
                            year = currentDate.year,
                            month = currentDate.month + 1,  // 다음 달
                            day = currentDate.day
                        )

                        kalendarState = kalendarState.copy(
                            newDate,
                            kalendarState.startOfWeek,
                            kalendarState.selectedDates
                        )
                    }
                ) {
                    Text("다음 달")
                }
            }

            MonthlyKalendar(
                kalendarState = kalendarState,
                titleUI = { year, month ->
                    BasicCalendarTitle(year, month)
                },
                dateCellUI = { modifier, dateNum, isSelected ->
                    // 위 modifier에 clickable이 들어있습니다. 원하는 컴포넌트가 클릭되도록 합니다.
                    BasicDateCell(modifier = modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(
                            if(isSelected)
                                Color.LightGray
                            else
                                Color.Unspecified
                        ),
                        dateNum = dateNum
                    )
                },
                weekLabelUI = { weekName ->
                    BasicWeekLabel(modifier = Modifier.padding(top = 12.dp).weight(1f), weekName)
                },
                onDateClick = { date ->
                    val selectedDates = kalendarState.selectedDates

                    val newSelectedDates = if (selectedDates.find {
                            it.simpleCalendarFormat() == date.simpleCalendarFormat()
                        } == null) {
                        selectedDates.plus(date)
                    } else {
                        selectedDates.filterNot {
                            it.simpleCalendarFormat() == date.simpleCalendarFormat()
                        }
                    }

                    kalendarState = kalendarState.copy(selectedDates = newSelectedDates)
                },
            )

            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "선택한 날 : ${
                    kalendarState.selectedDates.joinToString(",") {
                        it.simpleCalendarFormat()
                    }
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




//            printCalendar(calArray, Date.getWeekDays(DayOfWeek.SUNDAY))

            /* Date.kt 테스트 끝 */
        }
    }
}

@Composable
fun BasicCalendarTitle(year: Int, month: Int) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "${year}년 ${month}월"
    )
}

@Composable
fun BasicWeekLabel(modifier: Modifier, weekName: String) {
    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        text = weekName
    )
}

@Composable
fun BasicDateCell(modifier: Modifier = Modifier, dateNum: String) {
    Text(
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically),
        textAlign = TextAlign.Center,
        text = dateNum
    )
}