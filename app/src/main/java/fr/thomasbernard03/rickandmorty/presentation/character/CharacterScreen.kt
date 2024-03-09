package fr.thomasbernard03.rickandmorty.presentation.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fr.thomasbernard03.rickandmorty.R
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
//        item {
//            AsyncImage(
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier.fillMaxWidth(),
//                model = uiState.character?.image,
//                contentDescription = "Character image",
//                clipToBounds = true
//            )
//        }
//
//        item {
//            Column(
//                modifier = Modifier.padding(8.dp)
//            ) {
//                Text(
//                    text = uiState.character?.name ?: "-",
//                    style = MaterialTheme.typography.titleLarge
//                )
//
//                Text(
//                    text = uiState.character?.status?.toText() ?: "-",
//                    style = MaterialTheme.typography.titleMedium
//                )
//
//                Text(
//                    text = uiState.character?.species ?: "-",
//                    style = MaterialTheme.typography.titleMedium
//                )
//            }
//        }

        items(uiState.episodes){ episode ->
            EpisodeItem(
                episode = episode.episode,
                name = episode.name,
                airDate = episode.airDate,
                charactersImage = episode.characters.map { it.image })
        }

    }
}