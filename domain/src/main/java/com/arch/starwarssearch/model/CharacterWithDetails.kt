package com.arch.starwarssearch.model

data class CharacterWithDetails(
    val character: Character,
    val planet: Planet,
    val films: List<Film>,
    val species: List<Specie>,
    val starships: List<StarShip>,
    val vehicles: List<Vehicle>
)
