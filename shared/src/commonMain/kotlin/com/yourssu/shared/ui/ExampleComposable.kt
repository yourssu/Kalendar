package com.yourssu.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourssu.shared.date.Date
import com.yourssu.shared.date.DayOfWeek
import com.yourssu.shared.date.printCalendar

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

            Text(text = "안녕, $name! 여기는 shared 모듈입니다.")

            /* Date.kt 테스트 */

            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "=== Date.kt 테스트 ==="
            )

            val utcDate = Date() // UTC
            val nowDate = Date(currentTimeSecond) // 기기에게 전달받은 시간
            Text("- UTC -")
            Text(utcDate.simpleCalendarFormat())
            Text(utcDate.simpleTimeFormat())
            Text("- 기기 -")
            Text(nowDate.simpleCalendarFormat())
            Text(nowDate.simpleTimeFormat())

            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "${utcDate.getYear()}년 ${utcDate.getMonth()}월"
            )

            val weekStartDays = listOf(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
            val calArrays = weekStartDays.map { weekStart ->
                weekStart to Date.getMonthCalendar(utcDate.getYear(), utcDate.getMonth(), weekStart)
            }

            for((weekStart, calArray) in calArrays) {
                Row {
                    for(cell in Date.getWeekDays(weekStart).map { it.kor }) {
                        Text(modifier = Modifier.padding(top = 12.dp).weight(1f), text = cell)
                    }
                }

                for (row in calArray) {
                    Row {
                        for (cell in row) {
                            Text(modifier = Modifier.weight(1f), text = cell?.toString() ?: "")
                        }
                    }
                }
            }

//            printCalendar(calArray, Date.getWeekDays(DayOfWeek.SUNDAY))

            /* Date.kt 테스트 끝 */
        }
    }
}