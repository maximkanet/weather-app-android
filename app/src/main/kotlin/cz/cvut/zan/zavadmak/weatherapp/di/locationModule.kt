package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetCurrentLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetCurrentLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationDetailsUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationDetailsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.SearchLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.SearchLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.LocationDetailsViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.LocationsViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val locationModule = module {

    // --------- Use case -------------

    single<GetCurrentLocationUseCase> {
        GetCurrentLocationUseCaseImpl()
    }

    single<GetLocationDetailsUseCase> {
        GetLocationDetailsUseCaseImpl()
    }

    single<GetLocationsUseCase> {
        GetLocationsUseCaseImpl()
    }

    single<SearchLocationUseCase> {
        SearchLocationUseCaseImpl()
    }

    // ----------- View model -------------

    viewModel {
        LocationsViewModel(
            getLocationsUseCase = get()
        )
    }

    viewModel { params ->
        LocationDetailsViewModel(
            locationId = params.get(),
            getLocationDetailsUseCase = get()
        )
    }

    viewModel {
        SearchViewModel(
            searchLocationUseCase = get()
        )
    }

}