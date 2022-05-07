package com.arch.starwarssearch.di

import com.arch.starwarssearch.local.CharacterLocalDataSource
import com.arch.starwarssearch.local.CharacterLocalDataSourceImpl
import com.arch.starwarssearch.local.dao.CharacterDao
import com.arch.starwarssearch.remote.CharacterDetailsRemoteDatasource
import com.arch.starwarssearch.remote.CharacterDetailsRemoteDatasourceImpl
import com.arch.starwarssearch.remote.CharacterSearchRemoteDataSource
import com.arch.starwarssearch.remote.CharacterSearchRemoteDataSourceImpl
import com.arch.starwarssearch.remote.api.StarWarsService
import com.arch.starwarssearch.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideCharacterSearchRemoteDataSource(
        starWarsService: StarWarsService
    ): CharacterSearchRemoteDataSource {
        return CharacterSearchRemoteDataSourceImpl(starWarsService)
    }

    @Singleton
    @Provides
    fun provideCharacterDetailsRemoteDatasource(
        starWarsService: StarWarsService
    ): CharacterDetailsRemoteDatasource{
        return CharacterDetailsRemoteDatasourceImpl(starWarsService)
    }

    @Singleton
    @Provides
    fun provideCharacterLocalDataSource(
        characterDao: CharacterDao
    ): CharacterLocalDataSource{
        return CharacterLocalDataSourceImpl(characterDao)
    }

    @Singleton
    @Provides
    fun provideCharacterSearchRepository(
        characterSearchRemoteDataSource: CharacterSearchRemoteDataSource
    ): CharacterSearchRepository {
        return CharacterSearchRepositoryImpl(characterSearchRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideCharacterDetailsRepository(
        characterDetailsRemoteDatasource: CharacterDetailsRemoteDatasource
    ): CharacterDetailsRepository {
        return CharacterDetailsRepositoryImpl(characterDetailsRemoteDatasource)
    }

    @Singleton
    @Provides
    fun provideCharacterRepository(
        characterLocalDataSource: CharacterLocalDataSource
    ): CharacterRepository{
        return CharacterRepositoryImpl(characterLocalDataSource)
    }
}