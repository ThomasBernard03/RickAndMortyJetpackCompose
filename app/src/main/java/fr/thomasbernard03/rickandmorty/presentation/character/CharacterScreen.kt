package fr.thomasbernard03.rickandmorty.presentation.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.extensions.toColor
import fr.thomasbernard03.rickandmorty.commons.extensions.toText
import fr.thomasbernard03.rickandmorty.presentation.components.EpisodeItem
import fr.thomasbernard03.rickandmorty.presentation.components.Loader

@Composable
fun CharacterScreen(id : Long, uiState : CharacterUiState, onEvent : (CharacterEvent) -> Unit) {

    LaunchedEffect(id){
        onEvent(CharacterEvent.LoadCharacter(id))
    }

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            AsyncImage(
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                model = uiState.character?.image,
                contentDescription = "Character image",
            )
        }

        item {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                uiState.character?.status?.toColor() ?: Color.DarkGray,
                                shape = CircleShape
                            )
                            .size(10.dp))

                    Text(
                        text = "${uiState.character?.status?.toText() ?: ""} - ${uiState.character?.species ?: ""}",
                        style = MaterialTheme.typography.titleSmall)
                }

                Text(
                    text = uiState.character?.gender?.toText() ?: "",
                    style = MaterialTheme.typography.titleSmall)
            }
        }

        items(uiState.episodes){ episode ->

            var expanded by rememberSaveable { mutableStateOf(false) }

            EpisodeItem(
                episode = episode.episode,
                name = episode.name,
                airDate = episode.airDate,
                charactersImage = episode.characters.map { it.image },
                expanded = expanded
            ){
                expanded = !expanded
            }
        }

    }
}