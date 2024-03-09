package fr.thomasbernard03.rickandmorty

import android.app.Application
import androidx.room.Room
import fr.thomasbernard03.rickandmorty.commons.di.applicationModule
import fr.thomasbernard03.rickandmorty.data.local.ApplicationDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()

        val database: ApplicationDatabase =
            Room.databaseBuilder(this, ApplicationDatabase::class.java, "rickandmorty")
                .fallbackToDestructiveMigration() // If migrations needed delete all data and clear schema
                .build()

        startKoin {
            modules(applicationModule)
            androidContext(this@ApplicationController)

            modules(
                module {
                    single { database.characterDao() }
                    single { database.episodeDao() }
                    single { database.characterEpisodeDao() }
                }
            )
        }
    }
}