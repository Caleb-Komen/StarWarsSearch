package com.arch.starwarssearch.local.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "vehicles",
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
data class VehicleLocalEntity(
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
    @ColumnInfo(name = "character_id")
    val characterId: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
