package com.yourssu.kalendar.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MonthlyCalendar(
    currentMonth: LocalDate, // 현재 표시할 월 (예: 2024-01-01)
    selectedDate: LocalDate? = null,
    config: CalendarConfig = CalendarConfig(),
    onDateClick: (LocalDate) -> Unit = {},
    onMonthChange: (LocalDate) -> Unit = {},
    modifier: Modifier = Modifier
) {
    //val today = LocalDate.todayIn(TimeZone.currentSystemDefault()) // 실제 사용시에는 현재 날짜를 가져와야함
    val dummyDate = LocalDate(2024, 1, 1)
    Column(
        modifier = modifier
            .background(
                color = config.theme.backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        // Header
        if (config.showHeader) {
            CalendarHeader(
                currentMonth = currentMonth,
                config = config,
                onMonthChange = onMonthChange,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Day Headers
        if (config.showDayHeaders) {
            CalendarDayHeaders(
                firstDayOfWeek = config.firstDayOfWeek,
                theme = config.theme,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        // Calendar Grid
        CalendarGrid(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            //today = today,
            today = dummyDate,
            config = config,
            onDateClick = onDateClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun CalendarHeader(
    currentMonth: LocalDate,
    config: CalendarConfig,
    onMonthChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val headerText = buildString { // 헤더 글씨 (달/년) 원하면 (년/달)로 순서만 바꾸면 됨
        if (config.dateFormat.showMonth) {
            append(formatMonth(currentMonth.month, config.dateFormat.monthFormat))
            if (config.dateFormat.showYear) append(" ")
        }
        if (config.dateFormat.showYear) {
            append(formatYear(currentMonth.year, config.dateFormat.yearFormat))
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = when (config.headerStyle.alignment) {
            CalendarHeaderAlignment.START -> Arrangement.Start
            CalendarHeaderAlignment.CENTER -> Arrangement.Center
            CalendarHeaderAlignment.END -> Arrangement.End
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    val prevMonth = if (currentMonth.month == Month.JANUARY) {
                        LocalDate(currentMonth.year - 1, Month.DECEMBER, 1)
                    } else {
                        LocalDate(currentMonth.year, currentMonth.minus(1, DateTimeUnit.MONTH).month, 1)
                    }
                    onMonthChange(prevMonth)
                }
                .background(config.theme.primaryColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                color = config.theme.primaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // 달/년
        Text(
            text = headerText,
            fontSize = config.headerStyle.fontSize,
            fontWeight = config.headerStyle.fontWeight,
            color = config.theme.textColor
        )

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    val nextMonth = if (currentMonth.month == Month.DECEMBER) {
                        LocalDate(currentMonth.year + 1, Month.JANUARY, 1)
                    } else {
                        LocalDate(currentMonth.year, currentMonth.plus(1, DateTimeUnit.MONTH).month, 1)
                    }
                    onMonthChange(nextMonth)
                }
                .background(config.theme.primaryColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "›",
                color = config.theme.primaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CalendarDayHeaders(
    firstDayOfWeek: DayOfWeek,
    theme: CalendarTheme,
    modifier: Modifier = Modifier
) {
    val daysOfWeek = generateDaysOfWeekOrder(firstDayOfWeek)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        daysOfWeek.forEach { dayOfWeek ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getDayName(dayOfWeek),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = theme.secondaryTextColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun CalendarGrid(
    currentMonth: LocalDate,
    selectedDate: LocalDate?,
    today: LocalDate,
    config: CalendarConfig,
    onDateClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val monthStart = LocalDate(currentMonth.year, currentMonth.month, 1)
    val monthEnd = monthStart.plus(DatePeriod(months = 1)).minus(DatePeriod(days = 1))

    val startDate = getCalendarStartDate(monthStart, config.firstDayOfWeek)
    val endDate = getCalendarEndDate(monthEnd, config.firstDayOfWeek)

    val dates = generateDateRange(startDate, endDate)
    val weeks = dates.chunked(7)

    Column(modifier = modifier) {
        weeks.forEach { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                week.forEach { date ->
                    CalendarDay(
                        date = date,
                        isCurrentMonth = date.month == currentMonth.month,
                        isSelected = date == selectedDate,
                        isToday = date == today,
                        highlight = config.highlights.find { it.date == date },
                        theme = config.theme,
                        onDateClick = onDateClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

//날짜 칸
@Composable
private fun CalendarDay(
    date: LocalDate,
    isCurrentMonth: Boolean,
    isSelected: Boolean,
    isToday: Boolean,
    highlight: CalendarHighlight?,
    theme: CalendarTheme,
    onDateClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundColor = when {
        isSelected -> theme.selectedColor
        isToday -> theme.todayColor.copy(alpha = 0.3f)
        highlight?.backgroundColor != null -> highlight.backgroundColor
        isHovered -> theme.hoverColor
        else -> Color.Transparent
    }

    val textColor = when {
        isSelected -> theme.onPrimaryColor
        !isCurrentMonth -> theme.disabledColor
        highlight?.textColor != null -> highlight.textColor
        isToday -> theme.todayColor
        else -> theme.textColor
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .hoverable(interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onDateClick(date)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // 일
            Text(
                text = date.day.toString(),
                fontSize =12.sp,
                fontWeight = if (isToday || isSelected) FontWeight.Bold else FontWeight.Normal,
                color = textColor,
                textAlign = TextAlign.Center
            )

            // 일정 텍스트
            highlight?.text?.let { text ->
                Text(
                    text = text,
                    fontSize = 8.sp,
                    color = textColor.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }

            // 일정 dot
            highlight?.dotColor?.let { dotColor ->
                Spacer(modifier = Modifier.height(1.dp))
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(dotColor, CircleShape)
                )
            }
        }
    }
}

// 요일 순서 정하기
// 해당 요일을 넣으면 해당 요일을 시작으로 요일 배치 (일/월 선택가능)
private fun generateDaysOfWeekOrder(firstDayOfWeek: DayOfWeek): List<DayOfWeek> {
    val allDays = DayOfWeek.entries
    val firstDayIndex = allDays.indexOf(firstDayOfWeek)
    return allDays.drop(firstDayIndex) + allDays.take(firstDayIndex)
}

private fun getDayName(dayOfWeek: DayOfWeek): String {
    return when (dayOfWeek) {
        DayOfWeek.SUNDAY -> "일"
        DayOfWeek.MONDAY -> "월"
        DayOfWeek.TUESDAY -> "화"
        DayOfWeek.WEDNESDAY -> "수"
        DayOfWeek.THURSDAY -> "목"
        DayOfWeek.FRIDAY -> "금"
        DayOfWeek.SATURDAY -> "토"
    }
}

// 달 format NUMBER -> 01 로 나옴
private fun formatMonth(month: Month, format: MonthFormat): String {
    return when (format) {
        MonthFormat.FULL -> when (month) {
            Month.JANUARY -> "1월"
            Month.FEBRUARY -> "2월"
            Month.MARCH -> "3월"
            Month.APRIL -> "4월"
            Month.MAY -> "5월"
            Month.JUNE -> "6월"
            Month.JULY -> "7월"
            Month.AUGUST -> "8월"
            Month.SEPTEMBER -> "9월"
            Month.OCTOBER -> "10월"
            Month.NOVEMBER -> "11월"
            Month.DECEMBER -> "12월"
        }
        MonthFormat.NUMBER -> month.number.toString().padStart(2, '0')
    }
}

private fun formatYear(year: Int, format: YearFormat): String {
    return when (format) {
        YearFormat.FULL -> "${year}년"
        YearFormat.SHORT -> "${year % 100}년"
    }
}

//달력 시작 날짜
private fun getCalendarStartDate(monthStart: LocalDate, firstDayOfWeek: DayOfWeek): LocalDate {
    val monthStartDayOfWeek = monthStart.dayOfWeek
    val daysToSubtract = getDaysFromFirstDay(monthStartDayOfWeek, firstDayOfWeek)
    return monthStart.minus(DatePeriod(days = daysToSubtract))
}

//달력 끝 날짜
private fun getCalendarEndDate(monthEnd: LocalDate, firstDayOfWeek: DayOfWeek): LocalDate {
    val monthEndDayOfWeek = monthEnd.dayOfWeek
    val daysToAdd = 6 - getDaysFromFirstDay(monthEndDayOfWeek, firstDayOfWeek)
    return monthEnd.plus(DatePeriod(days = daysToAdd))
}

private fun getDaysFromFirstDay(dayOfWeek: DayOfWeek, firstDayOfWeek: DayOfWeek): Int {
    val allDays = DayOfWeek.entries
    val firstDayIndex = allDays.indexOf(firstDayOfWeek)
    val currentDayIndex = allDays.indexOf(dayOfWeek)
    return (currentDayIndex - firstDayIndex + 7) % 7
}

//전체 날짜 만들기
private fun generateDateRange(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    var currentDate = startDate

    while (currentDate <= endDate) {
        dates.add(currentDate)
        currentDate = currentDate.plus(DatePeriod(days = 1))
    }

    return dates
}

@Preview
@Composable
fun CalendarExample() {
    var currentMonth by remember { mutableStateOf(LocalDate(2024, 1, 1)) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var isDarkMode by remember { mutableStateOf(false) }

    val highlights = listOf(
        CalendarHighlight(
            date = LocalDate(2024, 1, 15),
            dotColor = Color.Red,
            text = "회의"
        ),
        CalendarHighlight(
            date = LocalDate(2024, 1, 20),
            backgroundColor = Color.Green.copy(alpha = 0.3f),
            textColor = Color.Green,
            text = "휴가"
        )
    )

    val config = CalendarConfig(
        showHeader = true,
        showDayHeaders = true,
        firstDayOfWeek = DayOfWeek.SUNDAY,
        theme = CalendarTheme(isDarkMode = isDarkMode),
        highlights = highlights
    )

    MonthlyCalendar(
        currentMonth = currentMonth,
        selectedDate = selectedDate,
        config = config,
        onDateClick = { date ->
            selectedDate = date
        },
        onMonthChange = { newMonth ->
            currentMonth = newMonth
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}