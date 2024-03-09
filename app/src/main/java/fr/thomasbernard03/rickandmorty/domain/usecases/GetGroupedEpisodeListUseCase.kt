package fr.thomasbernard03.rickandmorty.domain.usecases

import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import fr.thomasbernard03.rickandmorty.domain.models.EpisodeListModel
import fr.thomasbernard03.rickandmorty.domain.models.Resource
import fr.thomasbernard03.rickandmorty.domain.repositories.EpisodeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get

class GetGroupedEpisodeListUseCase(
    private val episodeRepository: EpisodeRepository = get(EpisodeRepository::class.java),
    private val resourcesHelper: ResourcesHelper = get(ResourcesHelper::class.java),
    private val ioDispatcher: CoroutineDispatcher = get(CoroutineDispatcher::class.java)
) {
    suspend operator fun invoke() : Resource<Map<String, List<EpisodeListModel>>> = withContext(ioDispatcher) {
        try {
            val episodes = episodeRepository.getEpisodeList()

            val result = episodes.groupBy { it.episode.substringBefore("E").replace("S", "") }

            Resource.Success(result)
        }
        catch (e : Exception){
            Resource.Error(resourcesHelper.getString(R.string.error_occured))
        }
    }
}