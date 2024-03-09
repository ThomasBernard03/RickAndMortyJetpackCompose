package fr.thomasbernard03.rickandmorty.domain.usecases

import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import fr.thomasbernard03.rickandmorty.domain.models.CharacterModel
import fr.thomasbernard03.rickandmorty.domain.models.Resource
import fr.thomasbernard03.rickandmorty.domain.repositories.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get

class GetCharacterUseCase(
    private val characterRepository: CharacterRepository = get(CharacterRepository::class.java),
    private val ioDispatcher: CoroutineDispatcher = get(CoroutineDispatcher::class.java),
    private val resourcesHelper: ResourcesHelper = get(ResourcesHelper::class.java)
) {
    suspend operator fun invoke(id : Long) : Resource<CharacterModel> = withContext(ioDispatcher){
        try {
            val character = characterRepository.getCharacter(id)
            Resource.Success(character)
        }
        catch (e : NullPointerException){
            Resource.Error(resourcesHelper.getString(R.string.error_character_not_found))
        }
        catch (e : Exception){
            e.printStackTrace()
            Resource.Error(resourcesHelper.getString(R.string.error_occured))
        }
    }
}