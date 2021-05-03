package com.fave.assignment.data.repository.movie

import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.datasource.movie.MovieRemoteDatasource
import com.fave.assignment.data.model.movies.MovieResponse
import com.fave.assignment.data.model.ResultData
import com.fave.assignment.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieRemoteDatasource
): MovieRepository {

    override suspend fun getMovies(sortBy: Constants.SortBy, pageNo:Int): MovieResponse {

        return when(val result = movieDataSource.getMovies(sortBy,pageNo)){
            is ResultData.Success -> result.data
            is ResultData.Error -> throw result.error
            else -> throw  Exception("Something went wrong. Please try again later.")
        }
    }

}