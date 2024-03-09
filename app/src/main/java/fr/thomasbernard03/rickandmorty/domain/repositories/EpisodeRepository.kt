package fr.thomasbernard03.rickandmorty.domain.repositories

import fr.thomasbernard03.rickandmorty.domain.models.EpisodeListModel
import fr.thomasbernard03.rickandmorty.domain.models.EpisodeModel

interface EpisodeRepository {

    suspend fun synchronizeEpisodes()

    suspend fun getEpisodesForCharacter(characterId : Long): List<EpisodeModel>

    suspend fun getEpisodeList(): List<EpisodeListModel>
}