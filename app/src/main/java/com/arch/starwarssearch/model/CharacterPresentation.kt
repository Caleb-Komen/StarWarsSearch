package com.arch.starwarssearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterPresentation(
    val name: String,
    val height: String,
    val birthYear: String,
    val url: String
): Parcelable
