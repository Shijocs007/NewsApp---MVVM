package com.shijo.newsapp.di

import com.shijo.newsapp.domain.repository.OnboardingRepository
import com.shijo.newsapp.domain.usecases.onboarding.GetOnboardingStatus
import com.shijo.newsapp.domain.usecases.onboarding.SaveOnboardingStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideSaveOnboardingStatusUsecase(repository: OnboardingRepository) =
        SaveOnboardingStatus(repository)

    @Provides
    fun provideGetOnboardingStatusUsecase(repository: OnboardingRepository) =
        GetOnboardingStatus(repository)

}