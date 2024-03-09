package fr.thomasbernard03.rickandmorty.presentation.characters

import fr.thomasbernard03.rickandmorty.domain.models.CharacterListModel

sealed class CharactersEvent {
    data object LoadCharacters : CharactersEvent()
    data class OnCharacterSelected(val character : CharacterListModel) : CharactersEvent()

    data class OnQueryChanged(val query : String) : CharactersEvent()
}