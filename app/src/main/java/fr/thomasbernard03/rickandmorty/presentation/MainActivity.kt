package fr.thomasbernard03.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fr.thomasbernard03.composents.NavigationBar
import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.commons.extensions.getSubtitle
import fr.thomasbernard03.rickandmorty.commons.extensions.getTitle
import fr.thomasbernard03.rickandmorty.commons.helpers.ErrorHelper
import fr.thomasbernard03.rickandmorty.commons.helpers.NavigationHelper
import fr.thomasbernard03.rickandmorty.domain.models.BottomBarItem
import fr.thomasbernard03.rickandmorty.presentation.navigation.navigationGraph
import fr.thomasbernard03.rickandmorty.presentation.theme.RickAndMortyTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class MainActivity(
    private val navigationHelper: NavigationHelper = get(NavigationHelper::class.java),
    private val errorHelper: ErrorHelper = get(ErrorHelper::class.java)
) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                val title = navController.currentBackStackEntry?.getTitle() ?: ""
                val subtitle = navController.currentBackStackEntry?.getSubtitle() ?: ""
                val showBackButton = navController.currentBackStackEntryAsState().value?.destination?.route?.contains("/") ?: false

                val bottomBarItems by remember {
                    mutableStateOf(
                        listOf(
                            BottomBarItem(label = R.string.characters, icon = R.drawable.character, route = "characters"),
                            BottomBarItem(label = R.string.episodes, icon = R.drawable.episode, route = "episodes"),
                            BottomBarItem(label = R.string.locations, icon = R.drawable.location, route = "locations"),
                        )
                    )
                }

                Scaffold(
                    topBar = {
                        NavigationBar(
                            title = title,
                            subtitle = subtitle,
                            showBackButton = showBackButton,
                            onBack = navigationHelper::goBack
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = MaterialTheme.colorScheme.primary,
                        ) {
                            bottomBarItems.forEach {
                                val selected = navController.currentBackStackEntryAsState().value?.destination?.route == it.route
                                NavigationBarItem(
                                    colors = NavigationBarItemDefaults.colors(
                                        unselectedIconColor = Color.White,
                                        selectedTextColor = Color.White,
                                        indicatorColor = Color.White,
                                        selectedIconColor = MaterialTheme.colorScheme.primary
                                    ),
                                    selected = selected,
                                    alwaysShowLabel = false,
                                    icon = {
                                        Icon(painter = painterResource(id = it.icon), contentDescription = stringResource(id = it.label))
                                    },
                                    label = {
                                        Text(text = stringResource(id = it.label))
                                    },
                                    onClick = {
                                        if (!selected){
                                            navController.navigate(it.route){
                                                popUpTo(navController.graph.id){
                                                    inclusive = true
                                                }
                                                launchSingleTop = true
                                            }
                                        }
                                    }
                                )
                            }
                        }
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
                            navigationGraph()
                        }
                    }
                }
            }
        }
    }
}