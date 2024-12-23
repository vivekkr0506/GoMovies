package com.vivek.gomovies.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vivek.gomovies.api.ApiService
import com.vivek.gomovies.model.Person

class SearchPeoplePagingSource(
    private val service: ApiService,
    private val query: String
) : PagingSource<Int, Person>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        val page = params.key ?: 1
        return try {
            val response = service.searchPeople(query, page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        TODO("Not yet implemented")
    }
}