package com.fave.assignment.data.network

import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.model.detail.Movie
import com.fave.assignment.data.model.movies.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface TMDBService {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("sort_by")  sortBy: String, @Query("page")  page: Int
    ): Response<MovieResponse>


    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int): Movie


}