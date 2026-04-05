package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetDeviceLastLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetDeviceLastLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetLastLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetLastLocationsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.RemoveSelectedLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.RemoveSelectedLocationsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.viewmodel.HomeViewModel
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.MarkLocationsAsUsedUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.MarkLocationsAsUsedUseCaseImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    // ------------ Use case ------------

    single<GetDeviceLastLocationUseCase> {
        GetDeviceLastLocationUseCaseImpl(
            repository = get()
        )
    }

    single<GetLastLocationsUseCase> {
        GetLastLocationsUseCaseImpl(
            repository = get()
        )
    }

    single<MarkLocationsAsUsedUseCase> {
        MarkLocationsAsUsedUseCaseImpl(
            repository = get()
        )
    }

    single<RemoveSelectedLocationsUseCase> {
        RemoveSelectedLocationsUseCaseImpl(
            repository = get()
        )
    }

    // ------------- View model -------------

    viewModel {
        HomeViewModel(
            getLastLocationsUseCase = get(),
            getDeviceLastLocationUseCase = get(),
            markLocationsAsUsedUseCase = get(),
            removeSelectedLocationsUseCase = get()
        )
    }

}