package fr.thomasbernard03.rickandmorty.commons.helpers

import kotlinx.coroutines.flow.SharedFlow

interface ErrorHelper {
    val sharedFlow : SharedFlow<String>
    fun showError(message : String)
}