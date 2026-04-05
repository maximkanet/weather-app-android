package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.source.LocationRemoteDataSource
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.source.LocationRemoteDataSourceImpl
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.api.MeteoApi
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.api.OpenMeteoApiImpl
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.source.WeatherRemoteDataSource
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.source.WeatherRemoteDataSourceImpl
import cz.cvut.zan.zavadmak.weatherapp.weather.data.repository.WeatherRepository
import cz.cvut.zan.zavadmak.weatherapp.weather.data.repository.WeatherRepositoryImpl
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

    /* ================== API ==================== */

    single<MeteoApi> {
        OpenMeteoApiImpl(
            client = get()
        )
    }

    /* ============== Data source ================ */

    single<WeatherRemoteDataSource> {
        WeatherRemoteDataSourceImpl(
            api = get()
        )
    }

    /* ============== Repositories ============= */

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            remoteDataSource = get()
        )
    }

    /* ============== Use case ============= */

    single<GetCurrentWeatherUseCase> {
        GetCurrentWeatherUseCaseImpl(
            repository = get()
        )
    }

    single<GetDailyWeatherUseCase> {
        GetDailyWeatherUseCaseImpl(
            repository = get()
        )
    }

    single<GetForecastUseCase> {
        GetForecastUseCaseImpl(
            repository = get()
        )
    }

    single<GetWeatherUnitsUseCase> {
        GetWeatherUnitsUseCaseImpl(
            repository = get()
        )
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