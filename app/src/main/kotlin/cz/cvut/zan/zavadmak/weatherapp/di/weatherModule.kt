package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetCurrentWeatherUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetCurrentWeatherUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetDailyWeatherUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetDailyWeatherUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetForecastUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetForecastUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetWeatherUnitsUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetWeatherUnitsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.CurrentWeatherViewModel
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.ForecastViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {

    /* =============== Providers ================ */

//    single {
//        LocationProvider(androidContext())
//    }

    /* ============== Repositories ============= */

    // ...

    /* ============== Use case ============= */

    single<GetCurrentWeatherUseCase> {
        GetCurrentWeatherUseCaseImpl()
    }

    single<GetDailyWeatherUseCase> {
        GetDailyWeatherUseCaseImpl()
    }

    single<GetForecastUseCase> {
        GetForecastUseCaseImpl()
    }

    single<GetWeatherUnitsUseCase> {
        GetWeatherUnitsUseCaseImpl()
    }

    /* ============== View models ============= */

    viewModel { params ->
        CurrentWeatherViewModel(
            longitude = params.get(),
            latitude = params.get(),
            getCurrentWeatherUseCase = get(),
            getDailyWeatherUseCase = get(),
            getWeatherUnitsUseCase = get()
        )
    }

    viewModel { params ->
        ForecastViewModel(
            longitude = params.get(),
            latitude = params.get(),
            getForecastUseCase = get(),
        )
    }
}