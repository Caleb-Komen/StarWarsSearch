package com.arch.starwarssearch.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithDetailsLocalEntity(
    @Embedded
    val characterLocalEntity: CharacterLocalEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "character_id"
    )
    val planetLocalEntity: PlanetLocalEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "character_id"
    )
    val filmLocalEntities: List<FilmLocalEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "character_id"
    )
    val specieLocalEntities: List<SpecieLocalEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "character_id"
    )
    val starshipLocalEntities: List<StarshipLocalEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "character_id"
    )
    val vehicleLocalEntities: List<VehicleLocalEntity>
)
