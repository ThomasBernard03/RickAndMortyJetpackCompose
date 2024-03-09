package fr.thomasbernard03.rickandmorty.presentation.episodes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.rickandmorty.presentation.components.EpisodesItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EpisodesScreen(uiState: EpisodesUiState, onEvent: (EpisodesEvent) -> Unit) {

    LaunchedEffect(Unit){
        onEvent(EpisodesEvent.OnLoadEpisodes)
    }

    
    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        columns = GridCells.Adaptive(minSize = 150.dp)
    ){
        items(uiState.episodes){ episode ->
            EpisodesItem(name = episode.name, episode = episode.episode, airDate = episode.airDate) {

            }
        }
    }
}