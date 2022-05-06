package com.arch.starwarssearch.local.mapper

import com.arch.starwarssearch.local.model.*
import com.arch.starwarssearch.model.*

fun CharacterLocalEntity.toDomain(): Character {
    return Character(name, height, birthYear, url)
}

fun CharacterWithDetailsLocalEntity.toDomain(): CharacterWithDetails{
    return CharacterWithDetails(
        characterLocalEntity.toDomain(),
        planetLocalEntity.toDomain(),
        filmLocalEntities.map { it.toDomain() },
        specieLocalEntities.map { it.toDomain() },
        starshipLocalEntities.map { it.toDomain() },
        vehicleLocalEntities.map { it.toDomain() }
    )
}

fun FilmLocalEntity.toDomain(): Film {
    return Film(title, producer, openingCrawl)
}

fun PlanetLocalEntity.toDomain(): Planet {
    return Planet(name, population, gravity)
}

fun SpecieLocalEntity.toDomain(): Specie {
    return Specie(name, language)
}

fun StarshipLocalEntity.toDomain(): StarShip {
    return StarShip(name, model, manufacturer, passengers)
}

fun VehicleLocalEntity.toDomain(): Vehicle {
    return Vehicle(name, model, manufacturer, passengers)
}