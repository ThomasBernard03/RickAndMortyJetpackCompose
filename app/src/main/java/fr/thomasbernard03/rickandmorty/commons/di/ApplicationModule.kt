package fr.thomasbernard03.rickandmorty.commons.di
import fr.thomasbernard03.rickandmorty.commons.helpers.ErrorHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.NavigationHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.PreferencesHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.implementations.ErrorHelperImpl
import fr.thomasbernard03.rickandmorty.commons.helpers.implementations.NavigationHelperImpl
import fr.thomasbernard03.rickandmorty.commons.helpers.implementations.PreferencesHelperImpl
import fr.thomasbernard03.rickandmorty.commons.helpers.implementations.ResourcesHelperImpl
import fr.thomasbernard03.rickandmorty.data.remote.ApiClient
import fr.thomasbernard03.rickandmorty.data.repositories.CharacterRepositoryImpl
import fr.thomasbernard03.rickandmorty.data.repositories.EpisodeRepositoryImpl
import fr.thomasbernard03.rickandmorty.domain.repositories.CharacterRepository
import fr.thomasbernard03.rickandmorty.domain.repositories.EpisodeRepository
import fr.thomasbernard03.rickandmorty.domain.usecases.GetCharacterEpisodesUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.GetCharacterListUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.GetCharacterUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.SynchronizeCharactersUseCase
import fr.thomasbernard03.rickandmorty.domain.usecases.SynchronizeEpisodesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val applicationModule = module {
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

    // Repositories
    single<CharacterRepository> { CharacterRepositoryImpl() }
    single<EpisodeRepository> { EpisodeRepositoryImpl() }

    // Remote
    single { ApiClient.apiService }


    // https://developer.android.com/kotlin/coroutines/coroutines-best-practices?hl=fr
    single<CoroutineDispatcher> { Dispatchers.IO }
}