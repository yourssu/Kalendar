package com.yourssu.shared.state

import com.yourssu.shared.date.Date
import com.yourssu.shared.date.DayOfWeek

data class KalendarState(
    val currentDate: Date, // 이것도 Date로 받을깜
    val startOfWeek: DayOfWeek,
    val selectedDates: List<Date>,
)
