package com.example.sampleapplication.feature_worldbank.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArchiveRepository @Inject constructor(private val api: ArchiveApi): Repository {

    override fun fetchData(): Flow<List<ArchiveSamplerResponse.File>> = flow{

        val response = api.getArchive()

        if(response.isSuccessful){
            response.body()?.files?.takeIf { it.isNotEmpty() }?.filterNotNull()?.let{
                emit(it)
            }?:run{
                throw Exception("No Data available")
            }
        }else{
            throw Exception(response.message())
        }
    }
}