package fr.thomasbernard03.rickandmorty.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class EpisodeDtoDown(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("air_date")
    val airDate: Date,

    @SerializedName("episode")
    val episode: String,

    @SerializedName("characters")
    val characters: List<String>,

    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String
)


//{
//    "id": 1,
//    "name": "Pilot",
//    "air_date": "December 2, 2013",
//    "episode": "S01E01",
//    "characters": [
//       "https://rickandmortyapi.com/api/character/1"
//    ],
//    "url": "https://rickandmortyapi.com/api/episode/1",
//    "created": "2017-11-10T12:56:33.798Z"
//}