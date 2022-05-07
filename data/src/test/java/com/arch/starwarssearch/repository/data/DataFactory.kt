package com.arch.starwarssearch.repository.data

import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.util.Utils.readFromFile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataFactory {
    fun getCharacter(): Character{
        return Gson()
            .fromJson(
                readFromFile("character.json"),
                object : TypeToken<Character>() {}.type
            )
    }

    fun getPlanet(): Planet{
        return Gson()
            .fromJson(
                readFromFile("character_planet.json"),
                object : TypeToken<Planet>() {}.type
            )
    }

    fun getFilm(): Film{
        return Gson()
            .fromJson(
                readFromFile("character_film.json"),
                object : TypeToken<Film>() {}.type
            )
    }

    fun getSpecie(): Specie {
        return Gson()
            .fromJson(
                readFromFile("character_specie.json"),
                object : TypeToken<Specie>() {}.type
            )
    }

    fun getStarship(): StarShip{
        return Gson()
            .fromJson(
                readFromFile("character_starship.json"),
                object : TypeToken<StarShip>() {}.type
            )
    }

    fun getVehicle(): Vehicle{
        return Gson()
            .fromJson(
                readFromFile("character_vehicle.json"),
                object : TypeToken<Vehicle>() {}.type
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