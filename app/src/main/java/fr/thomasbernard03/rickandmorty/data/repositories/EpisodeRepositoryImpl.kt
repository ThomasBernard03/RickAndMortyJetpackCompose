package fr.thomasbernard03.rickandmorty.data.repositories

import fr.thomasbernard03.rickandmorty.commons.helpers.PreferencesHelper
import fr.thomasbernard03.rickandmorty.data.local.dao.CharacterEpisodeDao
import fr.thomasbernard03.rickandmorty.data.local.dao.EpisodeDao
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEpisodeEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.EpisodeEntity
import fr.thomasbernard03.rickandmorty.data.remote.ApiService
import fr.thomasbernard03.rickandmorty.domain.models.EpisodeListModel
import fr.thomasbernard03.rickandmorty.domain.models.EpisodeModel
import fr.thomasbernard03.rickandmorty.domain.repositories.CharacterRepository
import fr.thomasbernard03.rickandmorty.domain.repositories.EpisodeRepository
import org.koin.java.KoinJavaComponent.get

class EpisodeRepositoryImpl(
    private val apiService: ApiService = get(ApiService::class.java),
    private val episodeDao: EpisodeDao = get(EpisodeDao::class.java),
    private val preferencesHelper: PreferencesHelper = get(PreferencesHelper::class.java)
) : EpisodeRepository {

    override suspend fun synchronizeEpisodes() {

        if (preferencesHelper.episodesSynchronizationDone)
            return

        episodeDao.nuke()

        var page = 1
        var again = true

        while (again){
            val episodes = apiService.getEpisodes(page++)
            again = episodes.info.next != null

            val episodesEntity = episodes.results.map { episode ->
                EpisodeEntity(
                    id = episode.id,
                    name = episode.name,
                    airDate = episode.airDate,
                    episode = episode.episode,
                    url = episode.url
                )
            }
            episodeDao.insert(episodesEntity)
        }

        preferencesHelper.episodesSynchronizationDone = true
    }

    override suspend fun getEpisodesForCharacter(characterId: Long): List<EpisodeModel> {
        val episodeEntity = episodeDao.getEpisodesForCharacter(characterId)

        return episodeEntity.map { episode ->
            val characters = episodeDao.getCharactersForEpisode(episode.id)
            EpisodeModel(
                id = episode.id,
                name = episode.name,
                airDate = episode.airDate,
                episode = episode.episode,
                characters = characters.map { EpisodeModel.CharacterModel(id = it.id, name = it.name, image = it.image) }
            )
        }
    }

    override suspend fun getEpisodeList(): List<EpisodeListModel> {
        val episodeEntity = episodeDao.getEpisodeList()

        return episodeEntity.map { episode ->
            EpisodeListModel(
                id = episode.id,
                name = episode.name,
                airDate = episode.airDate,
                episode = episode.episode,
            )
        }
    }
}