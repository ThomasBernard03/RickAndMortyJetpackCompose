package fr.thomasbernard03.rickandmorty.commons.extensions

import androidx.compose.ui.graphics.Color
import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import fr.thomasbernard03.rickandmorty.domain.models.Status
import org.koin.java.KoinJavaComponent.get

private val resourcesHelper : ResourcesHelper = get(ResourcesHelper::class.java)

fun Status.toText() : String =
    when(this){
        Status.Alive -> resourcesHelper.getString(R.string.status_alive)
        Status.Dead -> resourcesHelper.getString(R.string.status_dead)
        Status.Unknown -> resourcesHelper.getString(R.string.status_unknown)
    }

fun Status.toColor() : Color =
    when(this){
        Status.Alive -> Color.Green
        Status.Dead -> Color.Red
        Status.Unknown -> Color.Gray
    }