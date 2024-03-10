package fr.thomasbernard03.rickandmorty.commons.extensions

import androidx.navigation.NavBackStackEntry
import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import org.koin.java.KoinJavaComponent.get

private val resourcesHelper : ResourcesHelper = get(ResourcesHelper::class.java)

fun NavBackStackEntry.getTitle() : String {
    return when(this.destination.route){
        "characters" -> resourcesHelper.getString(R.string.characters)
        "characters/{id}?name={name}" -> resourcesHelper.getString(R.string.characters)
        "episodes" -> resourcesHelper.getString(R.string.episodes)
        "locations" -> resourcesHelper.getString(R.string.locations)
        else -> ""
    }
}

fun NavBackStackEntry.getSubtitle() : String {
    return when(this.destination.route){
        "characters/{id}?name={name}" -> {
            val characterName = this.arguments?.getString("name") ?: ""
            characterName
        }
        else -> ""
    }
}