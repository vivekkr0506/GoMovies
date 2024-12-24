package com.vivek.gomovies.model

data class PopularPeopleResponse(
    var page: Int? = null,
    var results: ArrayList<Person> = arrayListOf(),
    var totalPages: Int? = null,
    var totalResults: Int? = null,
    )


data class Person(
    var id: Int? = null,
    var knownForDepartment: String? = null,
    var name: String? = null,
    var originalName: String? = null,
    var popularity: Double? = null,
    var profile_path: String? = null,
){
    val real_path: String?
        get() = "https://image.tmdb.org/t/p/w500$profile_path"
}


data class PersonDetails(
    val name: String? = null,
    val biography: String? = null,
    val profile_path: String? = null,
) {
    val real_path: String?
        get() = "https://image.tmdb.org/t/p/w500$profile_path"
}


data class PersonImageResponse(
    val profiles: List<PersonImage>,
)

data class PersonImage(
    val file_path: String,
    val width: Int,
    val height: Int,
    val aspect_ratio: Double,
    val vote_average: Double,
    val vote_count: Int,
) {
    val fullImagePath: String
        get() = "https://image.tmdb.org/t/p/w500$file_path"
}