package cz.cvut.zan.zavadmak.weatherapp.settings.domain.model

sealed class WeatherUnit(
    val type: WeatherUnitType,
    val name: String,
    val displayName: String,
    val description: String,
) {

    class Fahrenheit : WeatherUnit(
        type = WeatherUnitType.Temperature,
        name = "fahrenheit",
        displayName = "°F",
        description = "Fahrenheit °F"
    )

    class Celsius : WeatherUnit(
        type = WeatherUnitType.Temperature,
        name = "celsius",
        displayName = "°C",
        description = "Celsius °C"
    )

    class MetersPerSecond : WeatherUnit(
        type = WeatherUnitType.WindSpeed,
        name = "ms",
        displayName = "m/s",
        description = "m/s"
    )

    class KilometersPerHour : WeatherUnit(
        type = WeatherUnitType.WindSpeed,
        name = "kmh",
        displayName = "km/h",
        description = "km/h"
    )

    class MilesPerHour : WeatherUnit(
        type = WeatherUnitType.WindSpeed,
        name = "mph",
        displayName = "mph",
        description = "mph"
    )

    class Knots : WeatherUnit(
        type = WeatherUnitType.WindSpeed,
        name = "kn",
        displayName = "knots",
        description = "Knots"
    )

    class Millimeters : WeatherUnit(
        type = WeatherUnitType.Precipitation,
        name = "mm",
        displayName = "mm",
        description = "mm"
    )

    class Inch : WeatherUnit(
        type = WeatherUnitType.Precipitation,
        name = "inch",
        displayName = "inch",
        description = "Inch"
    )

    fun valueToString(value: Double): String {
        return "${value.toInt()} $displayName"
    }

    companion object {

        fun allByType(type: WeatherUnitType): List<WeatherUnit> {
            return when (type) {
                WeatherUnitType.Temperature -> listOf(Celsius(), Fahrenheit())
                WeatherUnitType.WindSpeed -> listOf(
                    MetersPerSecond(),
                    KilometersPerHour(),
                    MilesPerHour(),
                    Knots()
                )

                WeatherUnitType.Precipitation -> listOf(
                    Millimeters(),
                    Inch()
                )
            }
        }

        fun findByType(
            type: WeatherUnitType,
            name: String
        ): WeatherUnit? {
            return when (type) {
                WeatherUnitType.Temperature -> allByType(type)
                    .find { it.name == name }

                WeatherUnitType.WindSpeed -> allByType(type)
                    .find { it.name == name }

                WeatherUnitType.Precipitation -> allByType(type)
                    .find { it.name == name }
            }
        }

        fun defaults(): List<WeatherUnit> {
            return listOf(
                Celsius(),
                MetersPerSecond(),
                Millimeters()
            )
        }

    }

}