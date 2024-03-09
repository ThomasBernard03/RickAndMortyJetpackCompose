package fr.thomasbernard03.rickandmorty.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val gson : Gson by lazy {
        GsonBuilder()
            .setLenient()
            .setDateFormat("MMMM d, yyyy")
            .create()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val httpClient : OkHttpClient by lazy {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}