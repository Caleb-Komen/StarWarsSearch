package com.arch.starwarssearch.local.dao

import androidx.room.*
import com.arch.starwarssearch.local.model.*

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<CharacterLocalEntity>

    @Transaction
    @Query("SELECT * FROM characters WHERE id = :url")
    suspend fun getCharacterByUrl(url: String): CharacterWithDetailsLocalEntity

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
    suspend fun insertCharacter(characterLocalEntity: CharacterWithDetailsLocalEntity){
        insertCharacter(characterLocalEntity.characterLocalEntity)
        insertPlanet(characterLocalEntity.planetLocalEntity)
        for(specie in characterLocalEntity.specieLocalEntities){
            insertSpecie(specie)
        }
        for(film in characterLocalEntity.filmLocalEntities){
            insertFilm(film)
        }
        for(starship in characterLocalEntity.starshipLocalEntities){
            insertStarship(starship)
        }
        for(vehicle in characterLocalEntity.vehicleLocalEntities){
            insertVehicle(vehicle)
        }
    }
}