package fr.thomasbernard03.rickandmorty.presentation.characters

import fr.thomasbernard03.rickandmorty.domain.models.CharacterListModel

data class CharactersUiState(
    val loading : Boolean = false,
    val loadingMessage : String = "",

    val characters : List<CharacterListModel> = emptyList(),
)