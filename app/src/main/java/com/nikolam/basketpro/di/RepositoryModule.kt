package com.nikolam.basketpro.di

import com.nikolam.basketpro.data.DrillRepository
import com.nikolam.basketpro.data.IDrillRepository
import com.nikolam.basketpro.data.remote.FirebaseFirestoreDataSource
import com.nikolam.basketpro.data.remote.FirebaseStorageDataSource
import org.koin.dsl.module

val repositoryModule = module {

    fun provideDrillRepository(remoteDataSource: FirebaseFirestoreDataSource, remoteStorageDataSource: FirebaseStorageDataSource): IDrillRepository {
        return DrillRepository(remoteDataSource, remoteStorageDataSource)
    }

    single { provideDrillRepository(get(), get()) }

}