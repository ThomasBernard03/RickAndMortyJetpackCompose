package fr.thomasbernard03.rickandmorty.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.thomasbernard03.rickandmorty.domain.models.Gender
import fr.thomasbernard03.rickandmorty.domain.models.Status

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val image: String,
    val gender: Gender,
    val url : String,
)