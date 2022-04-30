package com.arch.starwarssearch.local.model

import androidx.room.*

@Entity(
    tableName = "planets",
    foreignKeys = [
        ForeignKey(
            entity = CharacterLocalEntity::class,
            parentColumns = ["id"],
            childColumns = ["character_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("character_id")]
)
data class PlanetLocalEntity(
    val name: String,
    val population: String,
    val gravity: String,
    @ColumnInfo(name = "character_id")
    val characterId: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
