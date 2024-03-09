package fr.thomasbernard03.rickandmorty

import android.app.Application
import androidx.room.Room
import fr.thomasbernard03.rickandmorty.commons.helpers.ErrorHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.NavigationHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.PreferencesHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.implementations.ErrorHelperImpl
import fr.thomasbernard03.rickandmorty.commons.helpers.implementations.NavigationHelperImpl
import fr.thomasbernard03.rickandmorty.commons.helpers.implementations.PreferencesHelperImpl
import fr.thomasbernard03.rickandmorty.commons.helpers.implementations.ResourcesHelperImpl
import fr.thomasbernard03.rickandmorty.data.local.ApplicationDatabase
import fr.thomasbernard03.rickandmorty.data.remote.ApiClient
import fr.thomasbernard03.rickandmorty.data.repositories.CharacterRepositoryImpl
import fr.thomasbernard03.rickandmorty.data.repositories.EpisodeRepositoryImpl
import fr.thomasbernard03.rickandmorty.data.repositories.LocationRepositoryImpl
import fr.thomasbernard03.rickandmorty.domain.repositories.CharacterRepository
import fr.thomasbernard03.rickandmorty.domain.repositories.EpisodeRepository
import fr.thomasbernard03.rickandmorty.domain.repositories.LocationRepository
import fr.thomasbernard03.rickandmorty.domain.usecases.GetCharacterEpisodesUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.GetCharacterListUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.GetCharacterUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.GetGroupedEpisodeListUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.SynchronizeCharactersUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.SynchronizeEpisodesUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.SynchronizeLocationsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ApplicationController : Application() {

    private val database: ApplicationDatabase by lazy {
        Room.databaseBuilder(this, ApplicationDatabase::class.java, "rickandmorty")
            .fallbackToDestructiveMigration() // If migrations needed delete all data and clear schema
            .build()
    }


    private val applicationModule = module {
        // Helpers
        single<ResourcesHelper> { ResourcesHelperImpl() }
        single<NavigationHelper> { NavigationHelperImpl() }
        single<ErrorHelper> { ErrorHelperImpl() }
        single<PreferencesHelper> { PreferencesHelperImpl() }

        // UseCases
        single { GetCharacterListUseCase() }
        single { GetCharacterUseCase() }
        single { GetCharacterEpisodesUseCase() }
        single { SynchronizeEpisodesUseCase() }
        single { SynchronizeCharactersUseCase() }
        single { SynchronizeLocationsUseCase() }
        single { GetGroupedEpisodeListUseCase() }

        // Repositories
        single<CharacterRepository> { CharacterRepositoryImpl() }
        single<EpisodeRepository> { EpisodeRepositoryImpl() }
        single<LocationRepository> { LocationRepositoryImpl() }

        // Dao
        single { database.characterDao() }
        single { database.episodeDao() }
        single { database.characterEpisodeDao() }
        single { database.characterEpisodeDao() }
        single { database.locationDao() }
        single { database.characterLocationDao() }
        single { database.characterOriginDao() }

        // Remote
        single { ApiClient.apiService }

        // https://developer.android.com/kotlin/coroutines/coroutines-best-practices?hl=fr
        single<CoroutineDispatcher> { Dispatchers.IO }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(applicationModule)
            androidContext(this@ApplicationController)
        }
    }
}