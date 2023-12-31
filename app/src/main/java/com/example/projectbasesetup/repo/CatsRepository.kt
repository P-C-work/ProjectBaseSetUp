package com.example.projectbasesetup.repo


import com.example.projectbasesetup.models.CatsDataModel
import com.example.projectbasesetup.data.NetworkResult
import com.example.projectbasesetup.data.services.CatsService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CatsRepository(private val catsService: CatsService) {
    suspend fun fetchCats(limit: Int = 10) = flow<NetworkResult<List<CatsDataModel>>> {
        emit(NetworkResult.Loading())
        with(catsService.fetchCatsImages(limit)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }

}