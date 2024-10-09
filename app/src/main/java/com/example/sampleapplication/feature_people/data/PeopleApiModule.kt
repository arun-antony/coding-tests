package com.example.sampleapplication.feature_people.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object PeopleApiModule {

    @Provides
    fun provide(retrofit: Retrofit): PeopleApi{
        return retrofit.create(PeopleApi::class.java)
    }

}