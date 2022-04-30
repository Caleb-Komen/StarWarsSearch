package com.arch.starwarssearch.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.arch.starwarssearch.local.model.*

interface CharacterDao {
    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<CharacterLocalEntity>

    @Transaction
    @Query("SELECT * FROM characters")
    suspend fun getCharacterById(id: String): List<CharacterWithDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterLocalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(planet: PlanetLocalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecie(specie: SpecieLocalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmLocalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarship(starship: StarshipLocalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehicleLocalEntity)

    @Query("DELETE FROM characters WHERE id = :url")
    suspend fun deleteCharacterByUrl(url: String)

    @Query("DELETE FROM characters")
    suspend fun deleteAll()

    @Transaction
    suspend fun insertCharacter(character: CharacterWithDetails){
        insertCharacter(character.characterLocalEntity)
        insertPlanet(character.planetLocalEntity)
        for(specie in character.specieLocalEntities){
            insertSpecie(specie)
        }
        for(film in character.filmLocalEntities){
            insertFilm(film)
        }
        for(starship in character.starshipLocalEntities){
            insertStarship(starship)
        }
        for(vehicle in character.vehicleLocalEntities){
            insertVehicle(vehicle)
        }
    }
}