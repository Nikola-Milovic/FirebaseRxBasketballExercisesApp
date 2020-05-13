package com.nikolam.basketpro.di

import com.nikolam.basketpro.ui.drills.detail.DrillDetailViewModel
import com.nikolam.basketpro.ui.drills.list.DrillsListViewModel
import com.nikolam.basketpro.ui.drills.selection.DrillsSelectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { DrillsSelectionViewModel(get()) }

    viewModel { DrillsListViewModel(get()) }

    viewModel { DrillDetailViewModel(get()) }


}