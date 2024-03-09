package fr.thomasbernard03.rickandmorty.domain.repositories

interface LocationRepository {
    suspend fun synchronizeLocations()
}