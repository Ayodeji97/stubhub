package com.financials.stubhubevents.di

import android.content.Context
import com.financials.stubhubevents.business.repository.EventRepository
import com.financials.stubhubevents.business.repository.EventRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRepository(@ApplicationContext context: Context,
                          gson: Gson): EventRepository {
        return EventRepositoryImpl(gson, context)
    }
}