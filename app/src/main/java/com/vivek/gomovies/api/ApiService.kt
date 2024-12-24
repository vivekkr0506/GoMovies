package com.vivek.gomovies.api

import com.vivek.gomovies.model.PersonDetails
import com.vivek.gomovies.model.PersonImageResponse
import com.vivek.gomovies.model.PopularPeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("page") page: Int,
        @Header("Authorization") apiKey: String,
    ): PopularPeopleResponse

    @GET("search/person")
    suspend fun searchPeople(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Header("Authorization") apiKey: String,
    ): PopularPeopleResponse

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int, @Header("Authorization") apiKey: String,
    ): Response<PersonDetails>

    @GET("person/{person_id}/images")
    suspend fun getPersonImages(
        @Path("person_id") personId: Int, @Header("Authorization") apiKey: String,
    ): PersonImageResponse
}