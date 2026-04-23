package com.bakhmetow.data.di

import com.bakhmetow.data.remote.CountryApi
import com.bakhmetow.data.remote.MockRemoteCountryApi
import com.bakhmetow.data.repository.CountryRepositoryImpl
import com.bakhmetow.domain.repository.CountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCountryRepository(countryRepository: CountryRepositoryImpl): CountryRepository

    @Binds
    @Singleton
    @Named("MockApi")
    abstract fun bindMockApi(api: MockRemoteCountryApi): CountryApi
}