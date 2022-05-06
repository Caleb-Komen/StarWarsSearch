package com.arch.starwarssearch.local.mapper

import com.arch.starwarssearch.local.model.*
import com.arch.starwarssearch.model.*

fun Character.toEntity(): CharacterLocalEntity {
    return CharacterLocalEntity(name, height, birthYear, url)
}

fun CharacterWithDetails.toEntity(): CharacterWithDetailsLocalEntity{
    return CharacterWithDetailsLocalEntity(
        character.toEntity(),
        planet.toEntity(character.url),
        films.map { it.toEntity(character.url) },
        species.map { it.toEntity(character.url) },
        starships.map { it.toEntity(character.url) },
        vehicles.map { it.toEntity(character.url) }
    )
}

fun Film.toEntity(url: String): FilmLocalEntity {
    return FilmLocalEntity(title, producer, openingCrawl, url)
}

fun Planet.toEntity(url: String): PlanetLocalEntity {
    return PlanetLocalEntity(name, population, gravity, url)
}

fun Specie.toEntity(url: String): SpecieLocalEntity {
    return SpecieLocalEntity(name, language, url)
}

fun StarShip.toEntity(url: String): StarshipLocalEntity {
    return StarshipLocalEntity(name, model, manufacturer, passengers, url)
}

fun Vehicle.toEntity(url: String): VehicleLocalEntity {
    return VehicleLocalEntity(name, model, manufacturer, passengers, url)
}