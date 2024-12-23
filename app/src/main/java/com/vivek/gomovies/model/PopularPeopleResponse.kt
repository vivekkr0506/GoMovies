package com.vivek.gomovies.model

import com.google.gson.annotations.SerializedName

data class PopularPeopleResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<Person> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null,

    )


data class Person(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("known_for_department") var knownForDepartment: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("profile_path") var profilePath: String? = null,
)