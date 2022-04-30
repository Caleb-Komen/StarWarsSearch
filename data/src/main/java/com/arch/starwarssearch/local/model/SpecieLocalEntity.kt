package com.arch.starwarssearch.local.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "species",
    foreignKeys = [
        ForeignKey(
            entity = CharacterLocalEntity::class,
            parentColumns = ["id"],
            childColumns = ["character_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ],
    indices = [Index("character_id")]
)
data class SpecieLocalEntity(
    val name: String,
    val language: String,
    @ColumnInfo(name = "character_id")
    val characterId: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
