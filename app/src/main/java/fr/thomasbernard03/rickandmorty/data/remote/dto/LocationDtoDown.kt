package fr.thomasbernard03.rickandmorty.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LocationDtoDown(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("dimension")
    val dimension: String,

    @SerializedName("residents")
    val residents: List<String>,

    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String
)


//{
//    "id": 1,
//    "name": "Earth (C-137)",
//    "type": "Planet",
//    "dimension": "Dimension C-137",
//    "residents": [
//       "https://rickandmortyapi.com/api/character/38"
//    ],
//    "url": "https://rickandmortyapi.com/api/location/1",
//    "created": "2017-11-10T12:42:04.162Z"
//}