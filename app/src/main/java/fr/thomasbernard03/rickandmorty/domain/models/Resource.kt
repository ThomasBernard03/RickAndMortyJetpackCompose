package fr.thomasbernard03.rickandmorty.domain.models

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String) : Resource<T>()
}