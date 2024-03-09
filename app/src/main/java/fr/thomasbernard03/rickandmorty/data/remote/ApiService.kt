package fr.thomasbernard03.rickandmorty.data.remote

import fr.thomasbernard03.rickandmorty.data.remote.dto.BaseDtoDown
import fr.thomasbernard03.rickandmorty.data.remote.dto.CharacterDtoDown
import fr.thomasbernard03.rickandmorty.data.remote.dto.EpisodeDtoDown
import fr.thomasbernard03.rickandmorty.data.remote.dto.LocationDtoDown
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page : Int): BaseDtoDown<CharacterDtoDown>

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page : Int): BaseDtoDown<EpisodeDtoDown>

    @GET("location")
    suspend fun getLocations(@Query("page") page : Int): BaseDtoDown<LocationDtoDown>
}