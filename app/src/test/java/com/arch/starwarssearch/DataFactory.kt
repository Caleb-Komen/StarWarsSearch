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
            "It is a period of civil war.\n\nRebel spaceships, striking\n\nfrom a hidden base, have won\n\ntheir first victory against" +
                    "\n\nthe evil Galactic Empire.\n\n\n\nDuring the battle, Rebel\n\nspies managed to steal secret\r\nplans to the Empire's" +
                    "\n\nultimate weapon, the DEATH\n\nSTAR, an armored space\n\nstation with enough power\n\nto destroy an entire planet." +
                    "\n\n\n\nPursued by the Empire's\n\nsinister agents, Princess\n\nLeia races home aboard her\n\nstarship, custodian of the" +
                    "\n\nstolen plans that can save her\n\npeople and restore\n\nfreedom to the galaxy...."
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
}