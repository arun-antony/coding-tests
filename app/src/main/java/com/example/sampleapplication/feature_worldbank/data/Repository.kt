package com.example.sampleapplication.feature_worldbank.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun fetchData(): Flow<List<ArchiveSamplerResponse.File>>
}


@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule{

    @Binds
    fun binds(repository: ArchiveRepository): Repository
}