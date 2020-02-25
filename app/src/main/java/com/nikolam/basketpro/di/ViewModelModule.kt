package com.nikolam.basketpro.di

import com.nikolam.basketpro.ui.drills.detail.DrillDetailViewModel
import com.nikolam.basketpro.ui.drills.list.DrillsListViewModel
import com.nikolam.basketpro.ui.drills.selection.DrillsSelectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val viewModelModule = module {

    viewModel { DrillsSelectionViewModel(get(), get(named("main")), get(named("io"))) }

    viewModel { DrillsListViewModel(get()) }

    viewModel { DrillDetailViewModel(get()) }


}