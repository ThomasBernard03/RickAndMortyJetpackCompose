package fr.thomasbernard03.rickandmorty.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.composents.TextField
import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.domain.models.CharacterListModel
import fr.thomasbernard03.rickandmorty.domain.models.Gender
import fr.thomasbernard03.rickandmorty.domain.models.Status
import fr.thomasbernard03.rickandmorty.presentation.components.CharactersItem
import fr.thomasbernard03.rickandmorty.presentation.components.Loader

@Composable
fun CharactersScreen(uiState : CharactersUiState, onEvent : (CharactersEvent) -> Unit){

    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit){
        onEvent(CharactersEvent.LoadCharacters)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
                .padding(bottom = 4.dp),
            placeholder = stringResource(id = R.string.search_character),
            text = uiState.query,
            onTextChange = { onEvent(CharactersEvent.OnQueryChanged(it)) },
            keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null
                )
            }
        )


        if (uiState.loading){
            Loader(
                modifier = Modifier.fillMaxSize(),
                message = uiState.loadingMessage
            )
        }
        else {
            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.characters.filter {
                    it.name.contains(uiState.query, ignoreCase = true) ||
                    it.gender.name.contains(uiState.query, ignoreCase = true) ||
                    it.species.contains(uiState.query, ignoreCase = true) ||
                    it.status.name.contains(uiState.query, ignoreCase = true)
                }){ character ->
                    CharactersItem(
                        modifier = Modifier.fillMaxWidth(),
                        name = character.name,
                        status = character.status,
                        species = character.species,
                        image = character.image,
                        gender = character.gender
                    ){
                        onEvent(CharactersEvent.OnCharacterSelected(character))
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun CharactersScreenPreview2(){
    Surface {
        CharactersScreen(
            uiState = CharactersUiState(loading = true),
            onEvent = {}
        )
    }
}

@Composable
@Preview
private fun CharactersScreenPreview(){
    Surface {
        CharactersScreen(
            uiState = CharactersUiState(characters = listOf(
                CharacterListModel(
                    id = 1,
                    name = "Rick",
                    status = Status.Alive,
                    species = "Human",
                    gender = Gender.Male,
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                )
            )),
            onEvent = {}
        )
    }
}