package com.arch.starwarssearch.mapper

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.model.CharacterPresentation

fun Character.toEntity(): CharacterPresentation {
    return CharacterPresentation(name, height, birthYear, url)
}