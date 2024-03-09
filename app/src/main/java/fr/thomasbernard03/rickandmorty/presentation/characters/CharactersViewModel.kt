package fr.thomasbernard03.rickandmorty.presentation.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.helpers.ErrorHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.NavigationHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import fr.thomasbernard03.rickandmorty.domain.models.Resource
import fr.thomasbernard03.rickandmorty.domain.usecases.GetCharacterListUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.SynchronizeCharactersUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.SynchronizeEpisodesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class CharactersViewModel(
    private val getCharacterListUseCase: GetCharacterListUseCase = get(GetCharacterListUseCase::class.java),
    private val synchronizeEpisodesUseCase: SynchronizeEpisodesUseCase = get(SynchronizeEpisodesUseCase::class.java),
    private val navigationHelper: NavigationHelper = get(NavigationHelper::class.java),
    private val errorHelper: ErrorHelper = get(ErrorHelper::class.java),
    private val resourcesHelper: ResourcesHelper = get(ResourcesHelper::class.java),
    private val synchronizeCharactersUseCase: SynchronizeCharactersUseCase = get(SynchronizeCharactersUseCase::class.java)
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    fun onEvent(event : CharactersEvent){
        when(event){
            is CharactersEvent.LoadCharacters -> {
                loadEpisodes()
            }
            is CharactersEvent.OnCharacterSelected -> {
                navigationHelper.navigateTo(NavigationHelper.Destination.Character(event.character.id, event.character.name))
            }
        }
    }

    private fun loadEpisodes(){
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, loadingMessage = resourcesHelper.getString(R.string.loading_episodes)) }

            when(val result = synchronizeEpisodesUseCase()){
                is Resource.Success -> {
                    _uiState.update { it.copy(loadingMessage = resourcesHelper.getString(R.string.loading_characters)) }
                    loadCharacters()
                }
                is Resource.Error -> {
                    errorHelper.showError(result.message)
                    _uiState.update { it.copy(loading = false) }
                }
            }
        }
    }

    private suspend fun loadCharacters(){
        when(val result = synchronizeCharactersUseCase()){
            is Resource.Success -> {
                getCharacters()
            }
            is Resource.Error -> {
                errorHelper.showError(result.message)
                _uiState.update { it.copy(loading = false) }
            }
        }
    }

    private suspend fun getCharacters(){
        when(val result = getCharacterListUseCase()){
            is Resource.Success -> {
                _uiState.update { it.copy(loading = false, characters = result.data) }
            }
            is Resource.Error -> {
                errorHelper.showError(result.message)
                _uiState.update { it.copy(loading = false) }
            }
        }
    }
}