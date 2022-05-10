package com.arch.starwarssearch.mapper

import com.arch.starwarssearch.model.*

fun CharacterPresentation.toDomain(): Character {
    return Character(name, height, birthYear, url)
}

fun PlanetPresentation.toDomain(): Planet {
    return Planet(name, population, gravity)
}

fun SpeciePresentation.toDomain(): Specie {
    return Specie(name, language)
}

fun FilmPresentation.toDomain(): Film {
    return Film(title, producer, openingCrawl)
}

fun StarshipPresentation.toDomain(): StarShip {
    return StarShip(name, model, manufacturer, passengers)
}

fun VehiclePresentation.toDomain(): Vehicle {
    return Vehicle(name, model, manufacturer, passengers)
}

fun CharacterWithDetailsPresentation.toDomain(): CharacterWithDetails {
    return CharacterWithDetails(
        character.toDomain(),
        planet.toDomain(),
        films.map { it.toDomain() },
        species.map { it.toDomain() },
        starships.map { it.toDomain() },
        vehicles.map { it.toDomain() }
    )
}