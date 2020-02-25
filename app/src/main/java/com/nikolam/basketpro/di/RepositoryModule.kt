package com.nikolam.basketpro.di

import com.nikolam.basketpro.data.DataSource
import com.nikolam.basketpro.data.DrillRepository
import com.nikolam.basketpro.data.remote.FirebaseFirestoreDataSource
import com.nikolam.basketpro.data.remote.FirebaseStorageDataSource
import org.koin.dsl.module

val repositoryModule = module {

    fun provideDrillRepository(remoteDataSource: FirebaseFirestoreDataSource, remoteStorageDataSource: FirebaseStorageDataSource): DrillRepository {
        return DrillRepository(remoteDataSource, remoteStorageDataSource)
    }

    single { provideDrillRepository(get(), get()) }

}