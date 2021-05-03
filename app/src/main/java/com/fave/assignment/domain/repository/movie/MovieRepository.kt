package com.fave.assignment.domain.repository.movie

import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.model.movies.MovieResponse

interface MovieRepository {
    suspend fun getMovies(sortBy: Constants.SortBy,pageNo:Int): MovieResponse
}