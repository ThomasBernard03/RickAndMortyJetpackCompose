package fr.thomasbernard03.rickandmorty.domain.repositories

import fr.thomasbernard03.rickandmorty.domain.models.CharacterListModel
import fr.thomasbernard03.rickandmorty.domain.models.CharacterModel
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.jvm.Throws

interface CharacterRepository {
    @Throws(HttpException::class, UnknownHostException::class)
    suspend fun getCharacterList(): List<CharacterListModel>

    @Throws(NullPointerException::class, HttpException::class, UnknownHostException::class)
    suspend fun getCharacter(id : Long): CharacterModel

    @Throws(HttpException::class, UnknownHostException::class)
    suspend fun synchronizeCharacters()
}