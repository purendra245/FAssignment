package com.fave.assignment.data.datasource.movie

import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.model.movies.MovieResponse
import com.fave.assignment.data.model.ResultData

interface MovieRemoteDatasource {
    suspend fun getMovies(sortBy: Constants.SortBy,page: Int): ResultData<MovieResponse>
}