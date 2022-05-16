package com.arch.starwarssearch.ui

fun extractCharacterNameInitials(characterName: String): String{
    val nameList = characterName.split(" ")
    val initials = StringBuilder()
    for (name in nameList){
        initials.append(name[0])
    }
    return initials.toString().uppercase()
}