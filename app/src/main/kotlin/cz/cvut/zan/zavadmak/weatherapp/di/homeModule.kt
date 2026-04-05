package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetLastLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetLastLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetLastLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetLastLocationsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    // ------------ Use case ------------

    single<GetLastLocationUseCase> {
        GetLastLocationUseCaseImpl(
            repository = get()
        )
    }

    single<GetLastLocationsUseCase> {
        GetLastLocationsUseCaseImpl()
    }

    // ------------- View model -------------

    viewModel {
        HomeViewModel(
            getLastLocationsUseCase = get(),
            getLastLocationUseCase = get()
        )
    }

}