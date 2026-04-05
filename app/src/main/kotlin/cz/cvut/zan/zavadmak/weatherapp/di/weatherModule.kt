package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.settings.data.provider.SettingsProvider
import cz.cvut.zan.zavadmak.weatherapp.settings.data.provider.SettingsProviderImpl
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.GetWeatherUnitsUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.GetWeatherUnitsUseCaseImpl
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
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.CurrentWeatherViewModel
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel.ForecastViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {

    /* ================== API ==================== */

    single<MeteoApi> {
        OpenMeteoApiImpl(
            client = get()
        )
    }

    // -------------------- Providers ------------------

    single<SettingsProvider> {
        SettingsProviderImpl(
            repository = get()
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

    single<GetLocationUseCase> {
        GetLocationUseCaseImpl(
            repository = get()
        )
    }

    /* ============== View models ============= */

    viewModel {
        CurrentWeatherViewModel(
            getCurrentWeatherUseCase = get(),
            getDailyWeatherUseCase = get(),
            getLocationUseCase = get(),
            settingsProvider = get(),
        )
    }

    viewModel {
        ForecastViewModel(
            getForecastUseCase = get(),
            changeForecastRangeUseCase = get(),
            settingsProvider = get(),
            getLocationUseCase = get(),
        )
    }
}