package cz.cvut.zan.zavadmak.weatherapp.di

import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.MainScreens
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.viewmodel.NavViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {

    viewModel {
        NavViewModel(initScreen = MainScreens.Weather)
    }

}