package com.nikolam.basketpro.com.nikolam.basketpro

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.nikolam.basketpro.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : MultiDexApplication() {


    val moduleList = listOf(
        viewModelModule, repositoryModule, datasourceModule, databaseModule,
        dispatchersModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@MyApp)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(moduleList)
        }
    }
}