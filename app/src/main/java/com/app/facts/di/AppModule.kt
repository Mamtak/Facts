package com.app.facts.di

import com.app.facts.data.repo.ApiHelper
import com.app.facts.domain.repo.ApiRepository
import com.app.facts.core.utils.coroutines.AppDispatcherProvider
import com.app.facts.core.utils.coroutines.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideApiHelper(apiHelper: ApiHelper): ApiRepository

    @Binds
    @Singleton
    abstract fun provideDispatcher(dispatcherProvider: AppDispatcherProvider): DispatcherProvider

}