package cz.cvut.zan.zavadmak.weatherapp.di

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.source.LocationLocalDataSource
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.source.LocationLocalDataSourceImpl
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.api.GeoLocationApi
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.api.NominatimApiImpl
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.source.LocationRemoteDataSource
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.source.LocationRemoteDataSourceImpl
import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepositoryImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.GetLocationsUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.RemoveLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.RemoveLocationUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationModule = module {

    /* =============== Providers ================ */

    single<FusedLocationProviderClient> {
        LocationServices.getFusedLocationProviderClient(androidContext())
    }

    // ---------- Api ----------

    single<GeoLocationApi> {
        NominatimApiImpl(
            client = get()
        )
    }

    // ---------- Data source ----------

    single<LocationRemoteDataSource> {
        LocationRemoteDataSourceImpl(
            api = get()
        )
    }

    single<LocationLocalDataSource> {
        LocationLocalDataSourceImpl(
            locationDao = get()
        )
    }

    // -------- Repository ----------

    single<LocationsRepository> {
        LocationsRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get(),
            fusedLocationClient = get(),
        )
    }

    // --------- Use case -------------

    single<GetLocationsUseCase> {
        GetLocationsUseCaseImpl(
            repository = get()
        )
    }

    single<RemoveLocationUseCase> {
        RemoveLocationUseCaseImpl(
            repository = get()
        )
    }

}