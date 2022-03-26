package com.arch.starwarssearch.repository

import com.arch.starwarssearch.model.*
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

    private fun readFromFile(fileName: String): String{
        return javaClass.classLoader?.let {
            it.getResource(fileName)?.readText()
        }!!
    }
}