package com.alpharays.composeclean.di

import com.alpharays.composeclean.data.UserRepositoryImpl
import com.alpharays.composeclean.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providesUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}