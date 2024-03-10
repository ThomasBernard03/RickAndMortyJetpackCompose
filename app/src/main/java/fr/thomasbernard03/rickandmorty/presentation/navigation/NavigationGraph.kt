package fr.thomasbernard03.rickandmorty.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fr.thomasbernard03.composents.animations.animatedComposable
import fr.thomasbernard03.rickandmorty.R
import fr.thomasbernard03.rickandmorty.presentation.character.CharacterScreen
import fr.thomasbernard03.rickandmorty.presentation.character.CharacterViewModel
import fr.thomasbernard03.rickandmorty.presentation.characters.CharactersScreen
import fr.thomasbernard03.rickandmorty.presentation.characters.CharactersViewModel
import fr.thomasbernard03.rickandmorty.presentation.episodes.EpisodesScreen
import fr.thomasbernard03.rickandmorty.presentation.episodes.EpisodesViewModel

fun NavGraphBuilder.navigationGraph() {
    composable(
        route = "characters",
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
        }
    ){
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
        val viewModel : CharacterViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        CharacterScreen(id = id, uiState = uiState, onEvent = viewModel::onEvent)
    }


    composable(
        route = "episodes",
        enterTransition = {
            if (initialState.destination.route == "locations") {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            } else {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        },
        exitTransition = {
            if (targetState.destination.route == "locations") {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            } else {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        }
    ){
        val viewModel : EpisodesViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        EpisodesScreen(uiState = uiState, onEvent = viewModel::onEvent)
    }


    composable(
        route = "locations",
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        }
    ){
    }
}