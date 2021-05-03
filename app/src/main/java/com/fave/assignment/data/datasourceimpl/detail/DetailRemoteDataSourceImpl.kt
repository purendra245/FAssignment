package com.fave.assignment.data.datasourceimpl.detail

import com.fave.assignment.data.datasource.derail.DetailRemoteDataSource
import com.fave.assignment.data.datasource.movie.MovieRemoteDatasource
import com.fave.assignment.data.model.detail.Movie
import com.fave.assignment.data.network.TMDBService
import javax.inject.Inject


class DetailRemoteDataSourceImpl
@Inject
constructor(
    private val tmdbService: TMDBService
): DetailRemoteDataSource {
    override suspend fun getMoviesDetail(movieId: Int): Movie {
        return  tmdbService.getMovie(movieId)
    }

}