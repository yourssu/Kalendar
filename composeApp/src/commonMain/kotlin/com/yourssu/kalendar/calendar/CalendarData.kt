package com.yourssu.kalendar.calendar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DayOfWeek


data class CalendarTheme(
    val isDarkMode: Boolean = false,
    val backgroundColor: Color = if (isDarkMode) Color(0xFF1E1E1E) else Color.White,
    val primaryColor: Color = Color(0xFF2196F3),
    val onPrimaryColor: Color = Color.White,
    val textColor: Color = if (isDarkMode) Color.White else Color.Black,
    val secondaryTextColor: Color = if (isDarkMode) Color.Gray else Color(0xFF666666),
    val dividerColor: Color = if (isDarkMode) Color(0xFF333333) else Color(0xFFE0E0E0),
    val hoverColor: Color = primaryColor.copy(alpha = 0.1f),
    val selectedColor: Color = primaryColor,
    val todayColor: Color = Color(0xFFFF9800),
    val disabledColor: Color = if (isDarkMode) Color(0xFF666666) else Color(0xFFCCCCCC)
)


// Header Style
data class CalendarHeaderStyle(
    val fontSize: TextUnit = 20.sp,
    val fontWeight: FontWeight = FontWeight.Bold,
    val alignment: CalendarHeaderAlignment = CalendarHeaderAlignment.CENTER
)

//Header 배치
enum class CalendarHeaderAlignment {
    START, CENTER, END
}

// 달력 날짜 형식
data class CalendarDateFormat(
    val showYear: Boolean = true,
    val showMonth: Boolean = true,
    val monthFormat: MonthFormat = MonthFormat.FULL,
    val yearFormat: YearFormat = YearFormat.FULL
)

// 달 표기
enum class MonthFormat {
    FULL, // January
    NUMBER // 01
}

// 년 표기
enum class YearFormat {
    FULL, // 2024
    SHORT // 24
}

data class CalendarHighlight(
    val date: LocalDate,
    val dotColor: Color? = null, // 하단 dot 색
    val backgroundColor: Color? = null, // 뒷 배경색
    val textColor: Color? = null, // 글자 색
    val text: String? = null
)

data class CalendarConfig(
    val showHeader: Boolean = true,
    val showDayHeaders: Boolean = true,
    val firstDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY,
    val theme: CalendarTheme = CalendarTheme(),
    val headerStyle: CalendarHeaderStyle = CalendarHeaderStyle(),
    val dateFormat: CalendarDateFormat = CalendarDateFormat(),
    val highlights: List<CalendarHighlight> = emptyList()
)