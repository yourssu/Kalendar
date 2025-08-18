package com.yourssu.shared.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding

data class CalendarColors(
    val headerTextColor: Color,
    val weekDayTextColor: Color,
    val dayTextColor: Color,
    val selectedDayTextColor: Color,
    val selectedDayBackgroundColor: Color
) {
    companion object {
        @Composable
        fun default() = CalendarColors(
            headerTextColor = MaterialTheme.colors.onSurface,
            weekDayTextColor = MaterialTheme.colors.onSurface,
            dayTextColor = MaterialTheme.colors.onSurface,
            selectedDayTextColor = MaterialTheme.colors.onPrimary,
            selectedDayBackgroundColor = MaterialTheme.colors.primary
        )
    }
}

data class HeaderStyle(
    val textStyle: TextStyle,
    val modifier: Modifier
)
{
    companion object {
        @Composable
        fun default() = HeaderStyle(
            textStyle = MaterialTheme.typography.h6,
            modifier = Modifier
        )
    }
}

data class WeekDayStyle(
    val textStyle: TextStyle,
    val modifier: Modifier
) {
    companion object {
        @Composable
        fun default() = WeekDayStyle(
            textStyle = MaterialTheme.typography.caption,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}


data class DateCellStyle(
    val textStyle: TextStyle,
    val modifier: Modifier
) {
    companion object {
        @Composable
        fun default() = DateCellStyle(
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
        )
    }
}