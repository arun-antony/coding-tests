package com.example.sampleapplication.feature_people.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PeopleRepository @Inject constructor(private val api: PeopleApi?) {

    fun getPeople(): Flow<PeopleData> = flow {

        val response = api.getPeople()

        if(response.isSuccessful){
            response.body()?.let{
                emit(it)
            }?:run{
                throw Exception("Unable to get the response")
            }
        }else{
            throw Exception("Api fetch failed: "+response.message())
        }
    }
}

@JvmOverloads
fun PeopleRepository.prnt(sam: String = ""){

}