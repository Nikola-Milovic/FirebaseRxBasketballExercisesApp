package com.nikolam.basketpro.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.single
import kotlin.coroutines.CoroutineContext

val dispatchersModule = module {

    single<CoroutineContext>(named("main")){Dispatchers.Main}

    single<CoroutineContext>(named("io")){Dispatchers.IO}



}