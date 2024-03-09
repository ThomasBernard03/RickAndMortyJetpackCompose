package fr.thomasbernard03.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.thomasbernard03.rickandmorty.data.local.dao.CharacterDao
import fr.thomasbernard03.rickandmorty.data.local.dao.CharacterEpisodeDao
import fr.thomasbernard03.rickandmorty.data.local.dao.EpisodeDao
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEpisodeEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.EpisodeEntity

@Database(
    entities = [
        CharacterEntity::class,
        EpisodeEntity::class,
        CharacterEpisodeEntity::class
    ],
    version = 1,

)
@TypeConverters(DateConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun characterEpisodeDao(): CharacterEpisodeDao
}