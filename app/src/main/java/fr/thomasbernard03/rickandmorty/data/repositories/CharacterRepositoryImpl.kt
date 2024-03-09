package fr.thomasbernard03.rickandmorty.data.repositories

import fr.thomasbernard03.rickandmorty.commons.helpers.PreferencesHelper
import fr.thomasbernard03.rickandmorty.data.local.dao.CharacterDao
import fr.thomasbernard03.rickandmorty.data.local.dao.CharacterEpisodeDao
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEpisodeEntity
import fr.thomasbernard03.rickandmorty.data.remote.ApiService
import fr.thomasbernard03.rickandmorty.domain.models.CharacterListModel
import fr.thomasbernard03.rickandmorty.domain.models.CharacterModel
import fr.thomasbernard03.rickandmorty.domain.repositories.CharacterRepository
import org.koin.java.KoinJavaComponent.get

class CharacterRepositoryImpl(
    private val apiService: ApiService = get(ApiService::class.java),
    private val characterDao: CharacterDao = get(CharacterDao::class.java),
    private val characterEpisodeDao: CharacterEpisodeDao = get(CharacterEpisodeDao::class.java),
    private val preferencesHelper: PreferencesHelper = get(PreferencesHelper::class.java),
) : CharacterRepository {

    override suspend fun synchronizeCharacters() {

        if (preferencesHelper.charactersSynchronizationDone)
            return

        characterDao.nuke()

        var page = 1
        var again = true

        while (again){
            val charactersDto = apiService.getCharacters(page++)
            again = charactersDto.info.next != null

            val charactersEntity = charactersDto.results.map { character ->
                CharacterEntity(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    type = character.type,
                    gender = character.gender,
                    url = character.url,
                    image = character.image
                )
            }

            characterDao.insertOrUpdate(charactersEntity)


            charactersDto.results.forEach { characterDto ->
                characterDto.episode.forEach {  episodeUrl ->
                    val episodeId = episodeUrl.substringAfterLast("/").toLong()

                    val characterEpisode = CharacterEpisodeEntity(
                        characterId = characterDto.id,
                        episodeId = episodeId
                    )
                    
                    characterEpisodeDao.insertCharacterEpisode(characterEpisode)
                }
            }

        }

        preferencesHelper.charactersSynchronizationDone = true
    }

    override suspend fun getCharacterList(): List<CharacterListModel> {

        val charactersDb = characterDao.getCharacters()

        return charactersDb.map { entity ->
            CharacterListModel(
                id = entity.id,
                name = entity.name,
                status = entity.status,
                species = entity.species,
                gender = entity.gender,
                image = entity.image)}
    }

    override suspend fun getCharacter(id: Long): CharacterModel {
        val character = characterDao.getCharacter(id)
        return CharacterModel(
            id = character.id,
            name = character.name,
            status = character.status,
            species = character.species,
            gender = character.gender,
            image = character.image)

    }
}