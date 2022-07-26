package com.example.androidcodingchallenge.di;

import com.example.androidcodingchallenge.data.repository.ListItemsRepository
import com.example.androidcodingchallenge.data.repository.ListItemsRepositoryImpl
import dagger.Binds
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindListRepository(listItemsRepository: ListItemsRepositoryImpl): ListItemsRepository
}
