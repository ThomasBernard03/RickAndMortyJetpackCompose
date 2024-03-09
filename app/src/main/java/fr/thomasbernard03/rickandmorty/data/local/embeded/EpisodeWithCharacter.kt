package fr.thomasbernard03.rickandmorty.data.local.embeded

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.CharacterEpisodeEntity
import fr.thomasbernard03.rickandmorty.data.local.entities.EpisodeEntity

data class EpisodeWithCharacters(
    @Embedded val episode: EpisodeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CharacterEpisodeEntity::class,
            parentColumn = "episodeId",
            entityColumn = "characterId"
        )
    )
    val characters: List<CharacterEntity>
)
