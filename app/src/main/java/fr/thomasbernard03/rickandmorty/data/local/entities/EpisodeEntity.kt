package fr.thomasbernard03.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class EpisodeEntity(
    @PrimaryKey
    val id: Long,
    val name : String,
    val airDate : Date,
    val episode : String,
    val url : String,
)