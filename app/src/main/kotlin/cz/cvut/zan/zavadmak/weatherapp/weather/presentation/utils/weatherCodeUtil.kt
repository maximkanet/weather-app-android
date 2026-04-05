package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils

import cz.cvut.zan.zavadmak.weatherapp.R

fun weatherCodeToDrawable(code: Int): Int {
    return when(code) {
        // Icons here ...
        else -> R.drawable.clear
    }
}

fun weatherCodeToString(code: Int): String {
    return when(code) {
        1 -> ""
        else -> "Unknown"
    }
}