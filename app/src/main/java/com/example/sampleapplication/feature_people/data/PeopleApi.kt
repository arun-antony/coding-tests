package com.example.sampleapplication.feature_people.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PeopleApi {

    @GET("api/people")
    suspend fun getPeople(): Response<PeopleData>
}