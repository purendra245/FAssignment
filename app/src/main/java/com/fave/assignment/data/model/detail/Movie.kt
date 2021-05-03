package com.fave.assignment.data.model.detail

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,


    @SerializedName("genres")
    var genres: List<Genre>? = null,


    @SerializedName("imdb_id")
    var imdbId: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("popularity")
    var popularity: Float = 0f,

    @SerializedName("poster_path")
    var posterPath: String? = null,


    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("revenue")
    var revenue: Int = 0,

    @SerializedName("runtime")
    var runtime: Int = 0,


    @SerializedName("status")
    var status: String? = null,

    @SerializedName("tagline")
    var tagline: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("vote_average")
    var voteAverage: Float = 0f,

    @SerializedName("vote_count")
    var voteCount: Int = 0,

    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>? = null

)