package fr.thomasbernard03.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterOriginEntity

@Dao
interface CharacterOriginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterOrigin(characterOriginEntity: CharacterOriginEntity)
}