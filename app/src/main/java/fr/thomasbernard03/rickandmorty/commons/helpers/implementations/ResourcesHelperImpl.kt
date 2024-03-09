package fr.thomasbernard03.rickandmorty.commons.helpers.implementations

import android.content.Context
import androidx.annotation.StringRes
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import org.koin.java.KoinJavaComponent.get

class ResourcesHelperImpl(private val context: Context = get(Context::class.java), ) : ResourcesHelper {
    override fun getString(@StringRes stringId: Int, formatArgs: Any?)
            = context.getString(stringId, formatArgs)
}