package fr.thomasbernard03.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val url: String
)