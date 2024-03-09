package fr.thomasbernard03.rickandmorty.data.remote.dto

import com.google.gson.annotations.SerializedName
import fr.thomasbernard03.rickandmorty.domain.models.Gender
import fr.thomasbernard03.rickandmorty.domain.models.Status

data class CharacterDtoDown(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("status")
    val status: Status,

    @SerializedName("species")
    val species: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("gender")
    val gender : Gender,

    @SerializedName("image")
    val image : String,

    @SerializedName("episode")
    val episode : List<String>,

    @SerializedName("url")
    val url : String,

    @SerializedName("origin")
    val origin : Location?,

    @SerializedName("location")
    val location : Location?,
){
    data class Location(
        @SerializedName("name")
        val name: String,

        @SerializedName("url")
        val url: String,
    )
}

//{
//    "id": 1,
//    "name": "Rick Sanchez",
//    "status": "Alive",
//    "species": "Human",
//    "type": "",
//    "gender": "Male",
//    "origin": {
//       "name": "Earth (C-137)",
//       "url": "https://rickandmortyapi.com/api/location/1"
//    },
//    "location": {
//       "name": "Citadel of Ricks",
//       "url": "https://rickandmortyapi.com/api/location/3"
//     },
//    "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
//    "episode": [
//       "https://rickandmortyapi.com/api/episode/1",
//    ],
//    "url": "https://rickandmortyapi.com/api/character/1",
//    "created": "2017-11-04T18:48:46.250Z"
//}