package fr.thomasbernard03.rickandmorty.domain.models

import java.util.Date

data class EpisodeListModel(
    val id: Long,
    val name: String,
    val airDate: Date,
    val episode: String
)