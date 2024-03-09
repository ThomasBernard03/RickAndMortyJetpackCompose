package fr.thomasbernard03.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.thomasbernard03.rickandmorty.data.local.embeded.EpisodeWithCharacters
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.EpisodeEntity

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM EpisodeEntity WHERE id IN (SELECT episodeId FROM CharacterEpisodeEntity WHERE characterId = :characterId)")
    suspend fun getEpisodesForCharacter(characterId: Long): List<EpisodeEntity>


    @Query("SELECT * FROM CharacterEntity WHERE id IN (SELECT characterId FROM CharacterEpisodeEntity WHERE episodeId = :episodeId)")
    suspend fun getCharactersForEpisode(episodeId: Long): List<CharacterEntity>

    @Insert
    suspend fun insert(episodesEntity: List<EpisodeEntity>)

    @Query("DELETE FROM episodeentity")
    suspend fun nuke()
}