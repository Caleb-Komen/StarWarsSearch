package com.arch.starwarssearch.di

import android.content.Context
import com.arch.starwarssearch.local.StarWarsDatabase
import com.arch.starwarssearch.local.dao.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideStarWarsDatabase(
        @ApplicationContext context: Context
    ): StarWarsDatabase{
        return StarWarsDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideCharacterDao(
        database: StarWarsDatabase
    ): CharacterDao{
        return database.characterDao()
    }
}