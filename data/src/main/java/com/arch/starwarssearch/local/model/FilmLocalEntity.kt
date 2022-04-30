package com.arch.starwarssearch.local.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "films",
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
data class FilmLocalEntity(
    val title: String,
    val producer: String,
    @ColumnInfo(name = "opening_crawl")
    val openingCrawl: String,
    @ColumnInfo(name = "character_id")
    val characterId: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
