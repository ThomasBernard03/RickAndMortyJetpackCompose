package fr.thomasbernard03.rickandmorty.presentation.episodes

sealed class EpisodesEvent {
    data object OnLoadEpisodes : EpisodesEvent()
}