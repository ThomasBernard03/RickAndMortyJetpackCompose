package fr.thomasbernard03.rickandmorty.presentation.episodes

import fr.thomasbernard03.rickandmorty.domain.models.EpisodeListModel

data class EpisodesUiState(
    val loading : Boolean = false,

    val episodes : Map<String, List<EpisodeListModel>> = emptyMap()
)