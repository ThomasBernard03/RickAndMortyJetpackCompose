package fr.thomasbernard03.rickandmorty.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BaseDtoDown<T>(
    @SerializedName("info")
    val info : Info,

    @SerializedName("results")
    val results : List<T>
){
    data class Info(
        @SerializedName("count")
        val count : Int,

        @SerializedName("pages")
        val pages : Int,

        @SerializedName("next")
        val next : String?,

        @SerializedName("prev")
        val prev : String?,
    )
}