package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.settings.data.local.source.SettingsLocalDataSource
import cz.cvut.zan.zavadmak.weatherapp.settings.data.local.source.SettingsLocalDataSourceImpl
import cz.cvut.zan.zavadmak.weatherapp.settings.data.repository.SettingsRepository
import cz.cvut.zan.zavadmak.weatherapp.settings.data.repository.SettingsRepositoryImpl
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.GetNotificationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.GetNotificationsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.GetWeatherUnitsUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.GetWeatherUnitsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.SetNotificationStateUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.SetNotificationStateUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.SetWeatherUnitUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.SetWeatherUnitUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.viewmodel.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {

    // ------------ Data source -----------

    single<SettingsLocalDataSource> {
        SettingsLocalDataSourceImpl(context = get())
    }

    // -------------- Repository --------------

    single<SettingsRepository> {
        SettingsRepositoryImpl(dataSource = get())
    }

    // ---------------- Use case ----------------

    single<SetWeatherUnitUseCase> {
        SetWeatherUnitUseCaseImpl(
            repository = get()
        )
    }

    single<GetWeatherUnitsUseCase> {
        GetWeatherUnitsUseCaseImpl(
            repository = get()
        )
    }

    single<GetNotificationsUseCase> {
        GetNotificationsUseCaseImpl(
            repository = get()
        )
    }

    single<SetNotificationStateUseCase> {
        SetNotificationStateUseCaseImpl(
            repository = get()
        )
    }

    // -------------- ViewModel --------------

    viewModel {
        SettingsViewModel(
            setWeatherUnitUseCase = get(),
            getWeatherUnitsUseCase = get(),
            getNotificationsUseCase = get(),
            setNotificationStateUseCase = get(),
        )
    }
}