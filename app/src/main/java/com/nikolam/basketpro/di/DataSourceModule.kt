package com.nikolam.basketpro.di

import com.nikolam.basketpro.data.remote.FirebaseFirestoreDataSource
import com.nikolam.basketpro.data.remote.FirebaseStorageDataSource
import org.koin.dsl.module


val datasourceModule = module {

    single{FirebaseFirestoreDataSource(get())}

    single{FirebaseStorageDataSource(get())}
}