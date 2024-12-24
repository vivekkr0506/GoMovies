package com.vivek.gomovies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.vivek.gomovies.BuildConfig
import com.vivek.gomovies.api.ApiService
import com.vivek.gomovies.model.PersonDetails
import com.vivek.gomovies.model.PersonImage
import com.vivek.gomovies.pagination.PeoplePagingSource
import com.vivek.gomovies.pagination.SearchPeoplePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(private val service: ApiService) {
    fun getPopularPeoplePaging() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { PeoplePagingSource(service) }
    ).flow

    fun searchPeopleStream(query: String) = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { SearchPeoplePagingSource(service, query) }
    ).flow

    fun getPersonDetails(personId: Int): Flow<PersonDetails> {
        return flow {
            val response = service.getPersonDetails(personId, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                emit(response.body() ?: PersonDetails())
            } else {
                // Handle error
            }
        }.catch { e ->
            // Handle exception
        }
    }

    suspend fun getPersonImages(personId: Int): List<PersonImage> {
        return service.getPersonImages(personId, BuildConfig.API_KEY).profiles
    }
}
