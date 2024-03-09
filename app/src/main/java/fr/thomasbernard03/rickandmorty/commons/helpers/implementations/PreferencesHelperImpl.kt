package fr.thomasbernard03.rickandmorty.commons.helpers.implementations

import android.content.Context
import android.content.SharedPreferences
import fr.thomasbernard03.rickandmorty.commons.helpers.PreferencesHelper
import org.koin.java.KoinJavaComponent.get

class PreferencesHelperImpl(context : Context = get(Context::class.java)) : BasePreferencesHelper(), PreferencesHelper {

    override val sharedPreferences: SharedPreferences
            = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    companion object {
        private const val PREFERENCES = "preferences"
        private const val LOCATIONS_SYNCHRONIZATION_SYNC_DONE = "locations_synchronization_sync_done"
        private const val EPISODES_SYNCHRONIZATION_SYNC_DONE = "episodes_synchronization_sync_done"
        private const val CHARACTERS_SYNCHRONIZATION_SYNC_DONE = "characters_synchronization_sync_done"
    }

    override var locationsSynchronizationDone : Boolean
        get() = getValue(LOCATIONS_SYNCHRONIZATION_SYNC_DONE, false)
        set(value) = setValue(LOCATIONS_SYNCHRONIZATION_SYNC_DONE, value)

    override var episodesSynchronizationDone : Boolean
        get() = getValue(EPISODES_SYNCHRONIZATION_SYNC_DONE, false)
        set(value) = setValue(EPISODES_SYNCHRONIZATION_SYNC_DONE, value)


    override var charactersSynchronizationDone : Boolean
        get() = getValue(CHARACTERS_SYNCHRONIZATION_SYNC_DONE, false)
        set(value) = setValue(CHARACTERS_SYNCHRONIZATION_SYNC_DONE, value)
}