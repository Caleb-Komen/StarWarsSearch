package com.arch.starwarssearch.remote.mapper

import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.remote.model.*

fun Character.toEntity(): CharacterResponse{
    return CharacterResponse(name, height, birthYear, url)
}

fun Film.toEntity(): FilmInfoResponse{
    return FilmInfoResponse(title, producer, openingCrawl)
}

fun Planet.toEntity(): PlanetInfoResponse{
    return PlanetInfoResponse(name, population, gravity)
}

fun Specie.toEntity(): SpecieInfoResponse{
    return SpecieInfoResponse(name, language)
}

fun StarShip.toEntity(): StarShipInfoResponse{
    return StarShipInfoResponse(name, model, manufacturer, passengers)
}

fun Vehicle.toEntity(): VehicleInfoResponse{
    return VehicleInfoResponse(name, model, manufacturer, passengers)
}