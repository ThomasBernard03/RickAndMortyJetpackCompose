package fr.thomasbernard03.rickandmorty.presentation.character

import fr.thomasbernard03.rickandmorty.domain.models.CharacterModel
import fr.thomasbernard03.rickandmorty.domain.models.EpisodeModel

data class CharacterUiState(
    val episodesLoading : Boolean = false,

    val character : CharacterModel? = null,
    val episodes : List<EpisodeModel> = emptyList()
)