import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.datetime.*

/**
 * 날짜 선택기 상단 헤더 UI
 *
 * @param currentMonthDate 현재 표시 중인 달의 아무 날짜 (보통 1일)
 * @param onPreviousMonth 이전 달로 이동하는 람다
 * @param onNextMonth 다음 달로 이동하는 람다
 */
@Composable
fun CustomDatePickerHeader(
    currentMonthDate: LocalDate,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    val headerText = "${currentMonthDate.year}년 ${currentMonthDate.monthNumber}월"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 아이콘 대신 텍스트와 clickable Modifier 사용
        Box(
            modifier = Modifier
                .size(48.dp) // IconButton과 유사한 터치 영역 확보
                .clickable(onClick = onPreviousMonth),
            contentAlignment = Alignment.Center
        ) {
            Text("<", style = MaterialTheme.typography.titleMedium)
        }

        Text(
            text = headerText,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        // 아이콘 대신 텍스트와 clickable Modifier 사용
        Box(
            modifier = Modifier
                .size(48.dp) // IconButton과 유사한 터치 영역 확보
                .clickable(onClick = onNextMonth),
            contentAlignment = Alignment.Center
        ) {
            Text(">", style = MaterialTheme.typography.titleMedium)
        }
    }
}

/**
 * 요일(일, 월, 화...) 표시 UI (kotlinx-datetime)
 */
@Composable
fun DayOfWeekHeader() {
    val daysOfWeek = listOf(
        DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY
    )

    Row(modifier = Modifier.fillMaxWidth()) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                text = dayOfWeek.toKoreanShort(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/**
 * 날짜 그리드 UI (kotlinx-datetime)
 *
 * @param currentMonthDate 현재 표시 중인 달의 아무 날짜 (보통 1일)
 * @param selectedDate 선택된 날짜
 * @param onDateSelected 날짜가 선택되었을 때 호출되는 람다
 */
@Composable
fun CalendarGrid(
    currentMonthDate: LocalDate,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    val firstDayOfMonth = currentMonthDate.copy(dayOfMonth = 1)
    val nextMonth = firstDayOfMonth.plus(1, DateTimeUnit.MONTH)
    val daysInMonth = firstDayOfMonth.periodUntil(nextMonth).days
    val firstDayOfWeekValue = firstDayOfMonth.dayOfWeek.isoDayNumber % 7
    val emptyCells = firstDayOfWeekValue
    val days = (1..daysInMonth).map { day -> firstDayOfMonth.copy(dayOfMonth = day) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(count = emptyCells) {
            Box(modifier = Modifier.aspectRatio(1f))
        }
        items(days) { date ->
            val isSelected = date == selectedDate
            val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
            val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(2.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(backgroundColor)
                    .clickable { onDateSelected(date) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

// DayOfWeek를 한글 요일로 변환하는 확장 함수
private fun DayOfWeek.toKoreanShort(): String {
    return when (this) {
        DayOfWeek.SUNDAY -> "일"
        DayOfWeek.MONDAY -> "월"
        DayOfWeek.TUESDAY -> "화"
        DayOfWeek.WEDNESDAY -> "수"
        DayOfWeek.THURSDAY -> "목"
        DayOfWeek.FRIDAY -> "금"
        DayOfWeek.SATURDAY -> "토"
    }
}

// LocalDate의 dayOfMonth를 직접 수정하기 위한 편의 함수
private fun LocalDate.copy(
    year: Int = this.year,
    monthNumber: Int = this.monthNumber,
    dayOfMonth: Int = this.dayOfMonth
): LocalDate {
    return LocalDate(year, monthNumber, dayOfMonth)
}