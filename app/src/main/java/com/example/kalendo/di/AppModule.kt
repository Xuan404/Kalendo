package com.example.kalendo.di

import com.example.kalendo.ui.viewmodel.CalendarViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCalendarViewModel(): CalendarViewModel = CalendarViewModel()
}