package com.dnevtukhova.photoeditor.di

import com.dnevtukhova.photoeditor.interactor.NewsListInteractor
import com.dnevtukhova.photoeditor.interactor.NewsListInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun provideNewsInteractor(
        implementation: NewsListInteractorImpl
    ): NewsListInteractor

}