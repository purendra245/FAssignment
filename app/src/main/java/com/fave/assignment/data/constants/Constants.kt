package com.fave.assignment.data.constants

object Constants {

    const val MOVIE_ID = "movie_id"
    const val MOVIE_TITLE = "movie_title"

    enum class SortBy(var value: String) {
        RELEASE_DATE_ASCENDING("release_date.asc"),
        RELEASE_DATE_DESCENDING("release_date.desc"),
        PRIMARY_RELEASE_DATE_ASCENDING("primary_release_date.asc"),
        PRIMARY_RELEASE_DATE_DESCENDING("primary_release_date.desc"),
        VOTE_AVERAGE_ASCENDING("vote_average.asc"),
        VOTE_AVERAGE_DESCENDING("vote_average.desc"),
        ORIGINAL_TITLE_ASCENDING("original_title.asc"),
        ORIGINAL_TITLE_DESCENDING("original_title.desc")


    }




}