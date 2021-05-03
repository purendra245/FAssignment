package com.fave.assignment.domain.usecase.detail

import com.fave.assignment.data.model.ResultData
import com.fave.assignment.data.model.detail.Movie
import com.fave.assignment.data.model.movies.MovieResponse
import com.fave.assignment.domain.repository.detail.MovieDetailRepository
import com.fave.assignment.domain.repository.movie.MovieRepository
import javax.inject.Inject


class GetMoviesDetailUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) {

    suspend fun getMoviesDetail(movieId:Int): ResultData<Movie> {

        val movieResponse = movieDetailRepository.getMoviesDetail(movieId)

        return ResultData.Success(movieResponse)
    }


}