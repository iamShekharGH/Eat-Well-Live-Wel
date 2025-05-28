package com.shekharhandigol.di

import com.shekharhandigol.DatastoreRepositoryImpl
import com.shekharhandigol.MainRepositoryImpl
import com.shekharhandigol.RoomDbRepositoryImpl
import com.shekharhandigol.SearchRecipesRepositoryImpl
import com.shekharhandigol.repository.DatastoreRepository
import com.shekharhandigol.repository.MainRepository
import com.shekharhandigol.repository.RoomDbRepository
import com.shekharhandigol.repository.SearchRecipesRepository
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
    abstract fun bindDatastoreRepository(impl: DatastoreRepositoryImpl): DatastoreRepository

    @Binds
    @Singleton
    abstract fun bindRoomDbRepositoryImpl(impl: RoomDbRepositoryImpl): RoomDbRepository

    @Binds
    @Singleton
    abstract fun bindSearchRecipesRepositoryImpl(impl: SearchRecipesRepositoryImpl): SearchRecipesRepository

    @Binds
    @Singleton
    abstract fun bindMainRepositoryImpl(impl: MainRepositoryImpl): MainRepository


}