package fr.thomasbernard03.rickandmorty.presentation.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.thomasbernard03.rickandmorty.commons.helpers.ErrorHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.NavigationHelper
import fr.thomasbernard03.rickandmorty.domain.models.Resource
import fr.thomasbernard03.rickandmorty.domain.usecases.GetCharacterEpisodesUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase = get(GetCharacterUseCase::class.java),
    private val getCharacterEpisodesUseCase: GetCharacterEpisodesUseCase = get(GetCharacterEpisodesUseCase::class.java),
    private val errorHelper: ErrorHelper = get(ErrorHelper::class.java),
    private val navigationHelper: NavigationHelper = get(NavigationHelper::class.java),
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterUiState())
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    fun onEvent(event : CharacterEvent){
        when(event){
            is CharacterEvent.LoadCharacter -> {
                getCharacter(event.id)
                loadEpisodes(event.id)
            }
        }
    }

    private fun getCharacter(id: Long) {
        viewModelScope.launch {
            when(val result = getCharacterUseCase(id)){
                is Resource.Success -> {
                    _uiState.update { it.copy(character = result.data) }
                }
                is Resource.Error -> {
                    errorHelper.showError(result.message)
                }
            }
        }
    }

    private fun loadEpisodes(characterId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(episodesLoading = true) }
            when(val result = getCharacterEpisodesUseCase(characterId)){
                is Resource.Success -> {
                    _uiState.update { it.copy(episodes = result.data, episodesLoading = false) }
                }
                is Resource.Error -> {
                    errorHelper.showError(result.message)
                    _uiState.update { it.copy(episodesLoading = false) }
                }
            }
        }
    }
}