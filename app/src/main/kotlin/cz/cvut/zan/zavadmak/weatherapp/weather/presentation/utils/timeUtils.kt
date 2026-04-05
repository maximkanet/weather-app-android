package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toInstant
import kotlin.time.Duration

fun calculateProgress(actual: LocalDateTime, begin: LocalDateTime, end: LocalDateTime): Float {
    val totalDuration = calcDuration(begin, end).inWholeMinutes
    val elapsed = calcDuration(begin, actual).inWholeMinutes.coerceIn(0, totalDuration)
    return elapsed / totalDuration.toFloat()
}

fun calcDuration(a: LocalDateTime, b: LocalDateTime): Duration {
    return b.toInstant(TimeZone.UTC).minus(a.toInstant(TimeZone.UTC))
}

fun LocalDateTime.formatTime() : String {
    val timeFormatter = LocalDateTime.Format {
        hour()
        char(':')
        minute()
    }
    return this.format(timeFormatter)
}

fun LocalDateTime.formatDate() : String {
    val timeFormatter = LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        char(' ')
        char('(')
        dayOfMonth()
        char('.')
        monthNumber()
        char(')')
    }
    return this.format(timeFormatter)
}