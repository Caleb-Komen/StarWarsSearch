package com.arch.starwarssearch

import com.arch.starwarssearch.model.*

object DataFactory {
    fun getCharacter(): Character{
        return Character(
            "Luke Skywalker",
            "172",
            "19 BBY",
            "/api/people/1/"
        )
    }

    fun getPlanet(): Planet{
        return Planet("Tatooine", "120000", "1")
    }

    fun getFilm(): Film{
        return Film(
            "A New Hope",
            "Gary Kurtz, Rick McCallum",
            "It is a period of civil war..."
        )
    }

    fun getSpecie(): Specie{
        return Specie("Human", "Galactic Basic")
    }

    fun getStarship(): StarShip{
        return StarShip(
            "X-wing",
            "T-65 X-wing",
            "Incom Corporation",
            "0"
        )
    }

    fun getVehicle(): Vehicle{
        return Vehicle(
            "Snowspeeder",
            "t-47 airspeeder",
            "Incom corporation",
            "0"
        )
    }

    val char1 = CharacterWithDetails(
        getCharacter(),
        getPlanet(),
        listOf(getFilm()),
        listOf(getSpecie()),
        listOf(getStarship()),
        listOf(getVehicle())
    )
    val char2 = CharacterWithDetails(
        Character("C-3PO", "167", "112BBY", "/api/people/2/"),
        getPlanet(),
        listOf(Film("The Empire Strikes Back","Gary Kurtz, Rick McCallum","It is a dark time for the Rebellion.")),
        listOf(getSpecie()),
        listOf(getStarship()),
        listOf(getVehicle())
    )
}