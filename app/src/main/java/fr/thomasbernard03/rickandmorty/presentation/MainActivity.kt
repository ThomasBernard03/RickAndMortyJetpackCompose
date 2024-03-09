package fr.thomasbernard03.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.thomasbernard03.composents.NavigationBar
import fr.thomasbernard03.composents.animations.animatedComposable
import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.helpers.ErrorHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.NavigationHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.ResourcesHelper
import fr.thomasbernard03.rickandmorty.presentation.character.CharacterScreen
import fr.thomasbernard03.rickandmorty.presentation.character.CharacterViewModel
import fr.thomasbernard03.rickandmorty.presentation.characters.CharactersScreen
import fr.thomasbernard03.rickandmorty.presentation.characters.CharactersViewModel
import fr.thomasbernard03.rickandmorty.presentation.theme.RickAndMortyTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class MainActivity(
    private val navigationHelper: NavigationHelper = get(NavigationHelper::class.java),
    private val errorHelper: ErrorHelper = get(ErrorHelper::class.java),
    private val resourcesHelper: ResourcesHelper = get(ResourcesHelper::class.java)
) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                var subtitle by remember { mutableStateOf("") }
                val showBackButton = navController.currentBackStackEntryAsState().value?.destination?.route != "characters"


                Scaffold(
                    topBar = {
                        NavigationBar(
                            title = stringResource(id = R.string.characters),
                            subtitle = subtitle,
                            showBackButton = showBackButton,
                            onBack = navigationHelper::goBack
                        )
                    },
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) {
                    LaunchedEffect(Unit) {
                        navigationHelper.sharedFlow.onEach { event ->
                            when(event){
                                is NavigationHelper.NavigationEvent.NavigateTo -> {
                                    navController.navigate(event.destination.route) {
                                        // avoiding multiple copies on the top of the back stack
                                        launchSingleTop = true

                                        if(!event.popupTo.isNullOrEmpty())
                                            popUpTo(event.popupTo)
                                    }
                                }
                                is NavigationHelper.NavigationEvent.GoBack -> {
                                    navController.navigateUp()
                                }
                            }
                        }.launchIn(this)


                    }

                    LaunchedEffect(Unit){
                        errorHelper.sharedFlow.onEach { message ->
                            scope.launch {
                                if (snackbarHostState.currentSnackbarData != null)
                                    snackbarHostState.currentSnackbarData!!.dismiss()

                                snackbarHostState.showSnackbar(message, withDismissAction = true)
                            }
                        }.launchIn(this)
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(navController = navController, startDestination = "characters"){
                            animatedComposable(route = "characters"){
                                subtitle = ""
                                val viewModel : CharactersViewModel = viewModel()
                                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                                CharactersScreen(uiState = uiState, onEvent = viewModel::onEvent)
                            }

                            animatedComposable(
                                route = "characters/{id}?name={name}",
                                arguments = listOf(
                                    navArgument("id"){
                                        type = NavType.LongType
                                        defaultValue = 1L
                                    },
                                    navArgument("name"){
                                        type = NavType.StringType
                                        defaultValue = ""
                                    }
                                )
                            ){ backStackEntry->
                                val id = backStackEntry.arguments?.getLong("id") ?: 1L
                                val name = backStackEntry.arguments?.getString("name") ?: ""

                                subtitle = name

                                val viewModel : CharacterViewModel = viewModel()
                                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                                CharacterScreen(id = id, uiState = uiState, onEvent = viewModel::onEvent)
                            }
                        }
                    }
                }

            }
        }
    }
}