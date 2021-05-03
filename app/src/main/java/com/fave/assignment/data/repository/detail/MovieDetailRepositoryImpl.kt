package com.fave.assignment.data.repository.detail

import com.fave.assignment.data.datasource.derail.DetailRemoteDataSource
import com.fave.assignment.data.datasource.movie.MovieRemoteDatasource
import com.fave.assignment.data.model.movies.MovieResponse
import com.fave.assignment.data.model.ResultData
import com.fave.assignment.data.model.detail.Movie
import com.fave.assignment.domain.repository.detail.MovieDetailRepository
import com.fave.assignment.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieDetailRepositoryImpl
@Inject constructor(
    private val detailRemoteDataSource: DetailRemoteDataSource
): MovieDetailRepository {
    override suspend fun getMoviesDetail(movieId: Int): Movie {
        return detailRemoteDataSource.getMoviesDetail(movieId)
    }


}