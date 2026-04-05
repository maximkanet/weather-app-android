package cz.cvut.zan.zavadmak.weatherapp.weather.domain.model

data class WeatherUnits(
    private val temperature: String,
    private val speed: String,
    private val precipitation: String
) {
    fun temperature(value: Double) : String {
        val _value = value.toInt()
        return (if(_value < 0) "-" else "+") + "$_value ${this.temperature}"
    }

    fun speed(value: Double) : String {
        return "$value ${this.speed}"
    }

    fun percents(value: Double) : String {
        return "$value %"
    }

    fun precipitation(value: Double) : String {
        return "$value ${this.precipitation}"
    }

    companion object {

        fun asDefault() : WeatherUnits {
            return WeatherUnits(
                temperature = "℃",
                speed = "m/s",
                precipitation = "mm"
            )
        }

    }

}