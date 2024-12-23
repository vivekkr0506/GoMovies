package com.vivek.gomovies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.vivek.gomovies.api.ApiService
import com.vivek.gomovies.pagination.PeoplePagingSource
import com.vivek.gomovies.pagination.SearchPeoplePagingSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(private val service: ApiService) {
    fun getPopularPeoplePaging() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { PeoplePagingSource(service) }
    ).flow
}
