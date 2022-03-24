package com.arch.starwarssearch.remote.mapper

import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.remote.model.*

fun CharacterResponse.toDomain(): Character {
    return Character(name, height, birthYear, url)
}

fun FilmInfoResponse.toDomain(): Film {
    return Film(title, producer, openingCrawl)
}

fun PlanetInfoResponse.toDomain(): Planet {
    return Planet(name, population, gravity)
}

fun SpecieInfoResponse.toDomain(): Specie {
    return Specie(name, language)
}

fun StarShipInfoResponse.toDomain(): StarShip {
    return StarShip(name, model, manufacturer, passengers)
}

fun VehicleInfoResponse.toDomain(): Vehicle{
    return Vehicle(name, model, manufacturer, passengers)
}