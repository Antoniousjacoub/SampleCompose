package com.antonious.kotlincomposesample.di

import com.antonious.kotlincomposesample.network.DisneyService
import com.antonious.kotlincomposesample.persistence.PosterDao
import com.antonious.kotlincomposesample.ui.main.DashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
    disneyService: DisneyService,
    posterDao: PosterDao): DashboardRepository {
    return DashboardRepository(disneyService, posterDao)
  }
}
