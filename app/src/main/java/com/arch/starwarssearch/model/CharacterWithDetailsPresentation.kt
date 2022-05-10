package com.arch.starwarssearch.model

data class CharacterWithDetailsPresentation(
    val character: CharacterPresentation,
    val planet: PlanetPresentation,
    val films: List<FilmPresentation>,
    val species: List<SpeciePresentation>,
    val starships: List<StarshipPresentation>,
    val vehicles: List<VehiclePresentation>
)
