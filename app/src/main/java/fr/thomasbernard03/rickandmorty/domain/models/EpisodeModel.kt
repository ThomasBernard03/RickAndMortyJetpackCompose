package fr.thomasbernard03.rickandmorty.domain.models

import java.util.Date

data class EpisodeModel(
    val id : Long,
    val name : String,
    val airDate : Date,
    val episode : String,
    val characters : List<CharacterModel>
) {
    data class CharacterModel(
        val id : Long,
        val image : String,
        val name : String
    )
}