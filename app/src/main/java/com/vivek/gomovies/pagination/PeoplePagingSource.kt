package com.vivek.gomovies.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vivek.gomovies.api.ApiService
import com.vivek.gomovies.model.Person

class PeoplePagingSource(private val service: ApiService) : PagingSource<Int, Person>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        val page = params.key ?: 1
        return try {
            val response = service.getPopularPeople(page,"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZGI2NmU4YWEzZGVkZGQ4ZjVjY2E4YjlkOWRkYTYwMiIsIm5iZiI6MTcyNTA5MTEzMC44LCJzdWIiOiI2NmQyY2QzYWI2MzAyZDFmNTQ5NjViODkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.YmH1v9Do0JOHJvt7lSZs7FI8-Srb8rPZKC8_RCVsYtI")
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