package com.fave.assignment.data.datasource.derail

import com.fave.assignment.data.model.ResultData
import com.fave.assignment.data.model.detail.Movie
import com.fave.assignment.data.model.movies.MovieResponse


interface DetailRemoteDataSource {
    suspend fun getMoviesDetail(movieId: Int): Movie
}