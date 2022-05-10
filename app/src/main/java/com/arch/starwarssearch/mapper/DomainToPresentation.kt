package com.arch.starwarssearch.mapper

import com.arch.starwarssearch.model.*

fun Character.toPresentation(): CharacterPresentation {
    return CharacterPresentation(name, height, birthYear, url)
}

fun Planet.toPresentation(): PlanetPresentation {
    return PlanetPresentation(name, population, gravity)
}

fun Specie.toPresentation(): SpeciePresentation {
    return SpeciePresentation(name, language)
}

fun Film.toPresentation(): FilmPresentation{
    return FilmPresentation(title, producer, openingCrawl)
}

fun StarShip.toPresentation(): StarshipPresentation{
    return StarshipPresentation(name, model, manufacturer, passengers)
}

fun Vehicle.toPresentation(): VehiclePresentation {
    return VehiclePresentation(name, model, manufacturer, passengers)
}

fun CharacterWithDetails.toPresentation(): CharacterWithDetailsPresentation{
    return CharacterWithDetailsPresentation(
        character.toPresentation(),
        planet.toPresentation(),
        films.map { it.toPresentation() },
        species.map { it.toPresentation() },
        starships.map { it.toPresentation() },
        vehicles.map { it.toPresentation() }
    )
}