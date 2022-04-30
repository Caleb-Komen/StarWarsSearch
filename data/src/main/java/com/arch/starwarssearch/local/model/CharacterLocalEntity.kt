package com.arch.starwarssearch.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "characters", indices = [Index("id")])
data class CharacterLocalEntity(
    val name: String,
    val height: String,
    @ColumnInfo(name = "birth_year")
    val birthYear: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val url: String
)
