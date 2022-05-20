package com.arch.starwarssearch.local

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arch.starwarssearch.local.dao.CharacterDao
import com.arch.starwarssearch.local.model.*

@Database(
    entities = [
        CharacterLocalEntity::class,
        FilmLocalEntity::class,
        PlanetLocalEntity::class,
        SpecieLocalEntity::class,
        StarshipLocalEntity::class,
        VehicleLocalEntity::class],
    version = 1
)
abstract class StarWarsDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {

        // For Singleton instantiation
        @Volatile
        @VisibleForTesting
        var instance: StarWarsDatabase? = null

        fun getInstance(context: Context): StarWarsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): StarWarsDatabase {
            return Room.databaseBuilder(
                context,
                StarWarsDatabase::class.java,
                "starwars_db"
            ).build()
        }
    }
}