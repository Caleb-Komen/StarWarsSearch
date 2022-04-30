package com.arch.starwarssearch.local

import androidx.room.Database
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
}