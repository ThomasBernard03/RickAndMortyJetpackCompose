package fr.thomasbernard03.rickandmorty.commons.extensions

import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import fr.thomasbernard03.rickandmorty.domain.models.Gender
import org.koin.java.KoinJavaComponent.get

private val resourcesHelper : ResourcesHelper = get(ResourcesHelper::class.java)

fun Gender.toText() : String =
    when(this){
        Gender.Female -> resourcesHelper.getString(R.string.gender_female)
        Gender.Male -> resourcesHelper.getString(R.string.gender_male)
        Gender.Genderless -> resourcesHelper.getString(R.string.gender_genderless)
        Gender.Unknown -> resourcesHelper.getString(R.string.gender_unknown)
    }
