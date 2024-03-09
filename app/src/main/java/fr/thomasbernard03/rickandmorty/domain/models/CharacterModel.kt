package fr.thomasbernard03.rickandmorty.domain.models

data class CharacterModel(
    val id : Long,
    val name : String,
    val status : Status,
    val species : String,
    val image : String,
    val gender : Gender,
)