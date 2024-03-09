package fr.thomasbernard03.rickandmorty.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomBarItem(
    @StringRes val label : Int,
    @DrawableRes val icon : Int,
    val route : String,
)