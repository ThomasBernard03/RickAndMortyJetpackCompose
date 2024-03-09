package fr.thomasbernard03.rickandmorty.presentation.episodes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.presentation.components.EpisodesItem

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun EpisodesScreen(uiState: EpisodesUiState, onEvent: (EpisodesEvent) -> Unit) {

    LaunchedEffect(Unit){
        onEvent(EpisodesEvent.OnLoadEpisodes)
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        uiState.episodes.forEach { (season, episodes) ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(),
                    text = "${stringResource(id = R.string.season_number)}$season",
                )
            }

            items(items = episodes){ episode ->
                EpisodesItem(name = episode.name, episode = episode.episode, airDate = episode.airDate) {

                }
            }
        }
    }
}