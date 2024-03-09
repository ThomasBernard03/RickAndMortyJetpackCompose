package fr.thomasbernard03.rickandmorty.domain.models

import com.google.gson.annotations.SerializedName

enum class Gender {
    Female,
    Male,
    Genderless,
    @SerializedName("unknown")
    Unknown
}