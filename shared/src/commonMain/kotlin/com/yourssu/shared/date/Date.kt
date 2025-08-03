package com.yourssu.shared.date

import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

class Date {

    private val epochSeconds: Long

    @OptIn(ExperimentalTime::class)
    constructor() {
        epochSeconds = Clock.System.now().epochSeconds
        init()
    }

    /**
     * @param seconds 1970.1.1 이후로 경과된 초
     */
    constructor(seconds: Long) {
        epochSeconds = seconds
        init()
    }

    constructor(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day

        var days = 0L

        // 1970년부터 해당 연도 전년까지의 총 일수
        if (year > 1970) {
            days += getDaysOfYears(1970..year-1)
        }

        // 해당 연도의 1월부터 해당 월 전월까지의 일수
        for (m in 1 until month) {
            days += getDaysInMonth(year, m)
        }

        // 해당 월의 일수
        days += day

        // 요일 계산 (1970.1.1은 목요일)
        daysOfWeek = DayOfWeek.entries[((days + 3) % 7).toInt()]

        // epochSeconds 계산
        epochSeconds = days * 24 * 3600

    }

    var year = 1970
        set(value) {
            field = value

            var days = 0L

            // 1970년부터 해당 연도 전년까지의 총 일수
            if (year > 1970) {
                days += getDaysOfYears(1970..year-1)
            }

            // 해당 연도의 1월부터 해당 월 전월까지의 일수
            for (m in 1 until month) {
                days += getDaysInMonth(year, m)
            }

            // 해당 월의 일수
            days += day

            // 요일 계산 (1970.1.1은 목요일)
            daysOfWeek = DayOfWeek.entries[((days + 3) % 7).toInt()]
        }

    var month = 1
        set(value) {
            field = value
            if(field > 12) {
                year++
                field = field - 12
            } else if(field < 1) {
                field = 12
                year--
            }

            var days = 0L

            // 1970년부터 해당 연도 전년까지의 총 일수
            if (year > 1970) {
                days += getDaysOfYears(1970..year-1)
            }

            // 해당 연도의 1월부터 해당 월 전월까지의 일수
            for (m in 1 until month) {
                days += getDaysInMonth(year, m)
            }

            // 해당 월의 일수
            days += day

            // 요일 계산 (1970.1.1은 목요일)
            daysOfWeek = DayOfWeek.entries[((days + 3) % 7).toInt()]
        }
    var day = 1
        set(value) {
            field = value
            if(field > getDaysInMonth(year, month)) {
                month += 1
                field = field - getDaysInMonth(year, month)
            } else if(field < 1) {
                month -= 1
                field = getDaysInMonth(year, month)
            }

            var days = 0L

            // 1970년부터 해당 연도 전년까지의 총 일수
            if (year > 1970) {
                days += getDaysOfYears(1970..year-1)
            }

            // 해당 연도의 1월부터 해당 월 전월까지의 일수
            for (m in 1 until month) {
                days += getDaysInMonth(year, m)
            }

            // 해당 월의 일수
            days += day

            // 요일 계산 (1970.1.1은 목요일)
            daysOfWeek = DayOfWeek.entries[((days + 3) % 7).toInt()]
        }
    private var daysOfWeek = DayOfWeek.THURSDAY

    private var currentTime: Duration = 0.seconds

    private fun init() {
        var days = epochSeconds / (3600 * 24) // 1970.1.1 기준으로 몇일이 지났는지 계산
        currentTime = (epochSeconds % (3600 * 24)).seconds // 시간(hh:MM:ss) 계산

        daysOfWeek = DayOfWeek.entries[((days+3) % 7).toInt()] // 1970.1.1은 목요일

        while(days >= getDaysOfYear(year)) { // 연도(Year)
            days -= getDaysOfYear(year)
            year++
        }

        while(days >= getDaysInMonth(year, month)) { // 월(Month)
            days -= getDaysInMonth(year, month)
            month++
        }

        day = days.toInt() + 1 // 일(Day)

    }


    /**
     * @return 요일을 반환합니다.
     */
    fun getDaysOfWeek() = daysOfWeek

    /**
     * @return 간단한 형식으로 반환합니다.
     * @sample exampleOfSimpleCalendarFormat
     */
    fun simpleCalendarFormat(): String = "${year}-" +
            "${month.toString().padStart(2, '0')}-" +
            "${day.toString().padStart(2, '0')}" +
            "(${getDaysOfWeek().kor})"

    fun simpleTimeFormat(): String =
        "${currentTime.inWholeHours.toString().padStart(2, '0')}:" +
                "${(currentTime.inWholeMinutes % 60).toString().padStart(2, '0')}:" +
                "${(currentTime.inWholeSeconds % 60).toString().padStart(2, '0')}"

    companion object {
        val DAYS_IN_MONTH: IntArray = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        fun isLeapYear(year: Int): Boolean = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

        /**
         * @return 해당 연도의 일 수
         */
        fun getDaysOfYear(year: Int): Int = if (isLeapYear(year)) 366 else 365


        /**
         * @param years IntRange로 연도 범위 넘겨주시면 됩니다 (ex. 2020..2025)
         * @return 연도 범위의 총 일 수
         */
        fun getDaysOfYears(years: IntRange): Int = years.sumOf { getDaysOfYear(it) }

        /**
         * @param year 윤년일 수 있으니 연도도 전달해주세요.
         * @return 해당 월의 일 수
         */
        fun getDaysInMonth(year: Int, month: Int): Int = if (month == 2 && isLeapYear(year)) 29 else DAYS_IN_MONTH[month - 1]

        /**
         * @param year 윤년일 수 있으니 연도도 전달해주세요.
         * @return 해당 월의 시작 요일
         */
        fun getFirstDayOfWeek(year: Int, month: Int): DayOfWeek {
            var days = getDaysOfYears(1970..year-1)
            for(i in 1..month-1) {
                days += getDaysInMonth(year, i)
            }
            return DayOfWeek.entries[(days+3) % 7]
        }

        /**
         * @param weekStart DayOfWeek.SUNDAY와 DayOfWeek.MONDAY 사용 가능합니다.
         * @return 요일 시작 위치에 따라 요일 배열 반환
         * @throws IllegalArgumentException 일요일, 월요일이 아닌 경우
         */
        fun getWeekDays(weekStart: DayOfWeek): List<DayOfWeek> {
            return when (weekStart) {
                DayOfWeek.SUNDAY -> {
                    val entries = DayOfWeek.entries
                    val sundayIndex = entries.indexOf(DayOfWeek.SUNDAY) // 6
                    entries.drop(sundayIndex) + entries.take(sundayIndex) // sunday + 나머지
                }
                DayOfWeek.MONDAY -> DayOfWeek.entries
                else -> throw IllegalArgumentException("요일 시작 위치는 일요일과 월요일만 지원합니다.")
            }
        }

        /**
         * 달력 한 달치 2차원 배열 반환 (빈 칸은 null)
         * @return List<List<Int?>> : 각 주(week)별 날짜 리스트
         */
        fun getMonthCalendar(year: Int, month: Int, weekStart: DayOfWeek): List<List<Int?>> {
            val daysInMonth = getDaysInMonth(year, month)
            val firstDayOfWeek = getFirstDayOfWeek(year, month)
            val weekDays = getWeekDays(weekStart)
            val firstDayIndex = weekDays.indexOf(firstDayOfWeek)

            val calendar = mutableListOf<List<Int?>>()
            var week = MutableList<Int?>(7) { null }
            var day = 1

            // 첫 주 빈칸 채우기
            for (i in firstDayIndex until 7) {
                if (day > daysInMonth) break
                week[i] = day++
            }
            calendar.add(week.toList())

            // 나머지 주
            while (day <= daysInMonth) {
                week = MutableList(7) { null }
                for (i in 0 until 7) {
                    if (day > daysInMonth) break
                    week[i] = day++
                }
                calendar.add(week.toList())
            }
            return calendar
        }

    }

    private fun exampleOfSimpleCalendarFormat(): String = "2025-07-19(화)"

}

fun stringfiyCalendar(calendar: List<List<Int?>>, weekDays: List<DayOfWeek>): String {
    val sb = StringBuilder()
    sb.append(weekDays.map { it.kor }.joinToString(" ") + "\n")
    for (week in calendar) {
        sb.append(week.joinToString(" ") { it?.toString()?.padStart(2, ' ') ?: "  " } + "\n")
    }
    return sb.toString()
}

fun printCalendar(calendar: List<List<Int?>>, weekDays: List<DayOfWeek>) {
    println(weekDays.map { it.kor }.joinToString(" "))
    for (week in calendar) {
        println(week.joinToString(" ") { it?.toString()?.padStart(2, ' ') ?: "".padStart(2, ' ') })
    }
}

