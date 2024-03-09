package fr.thomasbernard03.rickandmorty.domain.usecases

import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import fr.thomasbernard03.rickandmorty.domain.models.CharacterListModel
import fr.thomasbernard03.rickandmorty.domain.models.EpisodeListModel
import fr.thomasbernard03.rickandmorty.domain.models.Resource
import fr.thomasbernard03.rickandmorty.domain.repositories.EpisodeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get
import retrofit2.HttpException
import java.net.UnknownHostException

class GetEpisodeListUseCase(
    private val episodeRepository: EpisodeRepository = get(EpisodeRepository::class.java),
    private val resourcesHelper: ResourcesHelper = get(ResourcesHelper::class.java),
    private val ioDispatcher: CoroutineDispatcher = get(CoroutineDispatcher::class.java)
) {
    suspend operator fun invoke() : Resource<List<EpisodeListModel>> = withContext(ioDispatcher) {
        try {
            val characters = episodeRepository.getEpisodeList()
            Resource.Success(characters)
        }
        catch (e : Exception){
            Resource.Error(resourcesHelper.getString(R.string.error_occured))
        }
    }
}