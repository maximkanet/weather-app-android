package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.search.domain.usecase.SaveLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.search.domain.usecase.SaveLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.search.domain.usecase.SearchForLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.search.domain.usecase.SearchForLocationUseCaseImpl
import cz.cvut.zan.zavadmak.weatherapp.search.presentation.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    // ----------- Use case -------------

    single<SearchForLocationUseCase> {
        SearchForLocationUseCaseImpl(
            repository = get()
        )
    }

    single<SaveLocationUseCase> {
        SaveLocationUseCaseImpl(
            repository = get()
        )
    }

    // ----------- View model -------------

    viewModel {
        SearchViewModel(
            searchForLocationUseCase = get(),
            saveLocationUseCase = get(),
        )
    }
}