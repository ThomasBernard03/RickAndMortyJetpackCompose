package fr.thomasbernard03.rickandmorty.domain.models

import com.google.gson.annotations.SerializedName

enum class Status {
    Alive,
    Dead,
    @SerializedName("unknown")
    Unknown
}