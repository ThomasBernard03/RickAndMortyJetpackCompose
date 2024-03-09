package fr.thomasbernard03.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.thomasbernard03.rickandmorty.data.local.dao.CharacterDao
import fr.thomasbernard03.rickandmorty.data.local.dao.CharacterEpisodeDao
import fr.thomasbernard03.rickandmorty.data.local.dao.CharacterLocationDao
import fr.thomasbernard03.rickandmorty.data.local.dao.CharacterOriginDao
import fr.thomasbernard03.rickandmorty.data.local.dao.EpisodeDao
import fr.thomasbernard03.rickandmorty.data.local.dao.LocationDao
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEpisodeEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterLocationEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterOriginEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.EpisodeEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.LocationEntity

@Database(
    entities = [
        CharacterEntity::class,
        EpisodeEntity::class,
        CharacterEpisodeEntity::class,
        LocationEntity::class,
        CharacterLocationEntity::class,
        CharacterOriginEntity::class
    ],
    version = 1,

    )
@TypeConverters(DateConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    abstract fun episodeDao(): EpisodeDao
    abstract fun characterEpisodeDao(): CharacterEpisodeDao

    abstract fun locationDao(): LocationDao
    abstract fun characterLocationDao(): CharacterLocationDao
    abstract fun characterOriginDao(): CharacterOriginDao
}