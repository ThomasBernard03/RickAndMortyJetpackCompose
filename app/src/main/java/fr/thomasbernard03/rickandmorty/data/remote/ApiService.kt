package fr.thomasbernard03.rickandmorty.data.remote

import fr.thomasbernard03.rickandmorty.data.remote.dto.BaseDtoDown
import fr.thomasbernard03.rickandmorty.data.remote.dto.CharacterDtoDown
import fr.thomasbernard03.rickandmorty.data.remote.dto.EpisodeDtoDown
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): BaseDtoDown<CharacterDtoDown>

    @GET("character")
    suspend fun getCharacters(@Query("page") page : Int): BaseDtoDown<CharacterDtoDown>

    @GET
    suspend fun getCharacters(@Url url : String): CharacterDtoDown


    @GET
    suspend fun getEpisodes(@Url url : String): EpisodeDtoDown

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page : Int): BaseDtoDown<EpisodeDtoDown>
}