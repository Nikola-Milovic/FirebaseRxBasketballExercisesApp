package com.nikolam.basketpro.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val dispatchersModule = module {

  //  single<CoroutineContext>(named("main")){Dispatchers.Main}

    single<CoroutineContext>{Dispatchers.IO}



}