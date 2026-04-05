package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.api.NominatimApi
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.api.NominatimApiImpl
import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepositoryImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.AddLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.AddLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetCurrentLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetCurrentLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationDetailsUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationDetailsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.RemoveLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.RemoveLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.SearchForLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.SearchForLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.LocationDetailsViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.LocationsViewModel
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val locationModule = module {

    // ---------- Api ----------

    single<NominatimApi> {
        NominatimApiImpl()
    }

    // -------- Repository ----------

    single<LocationsRepository> {
        LocationsRepositoryImpl(
            nominatim = get()
        )
    }

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

    single<SearchForLocationUseCase> {
        SearchForLocationUseCaseImpl(
            repository = get()
        )
    }

    single<AddLocationUseCase> {
        AddLocationUseCaseImpl()
    }

    single<RemoveLocationUseCase> {
        RemoveLocationUseCaseImpl()
    }

    // ----------- View model -------------

    viewModel {
        LocationsViewModel(
            getLocationsUseCase = get(),
            addLocationUseCase = get(),
            removeLocationUseCase = get()
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
            searchForLocationUseCase = get()
        )
    }

}