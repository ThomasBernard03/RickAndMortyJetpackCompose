package fr.thomasbernard03.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEpisodeEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterLocationEntity

@Dao
interface CharacterLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterLocation(characterLocationEntity: CharacterLocationEntity)
}