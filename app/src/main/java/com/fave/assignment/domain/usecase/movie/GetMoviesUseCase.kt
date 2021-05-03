package com.fave.assignment.domain.usecase.movie

import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.model.movies.MovieResponse
import com.fave.assignment.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val dataRepository: MovieRepository) {

    suspend fun getMovies(sortBy: Constants.SortBy,pageNo:Int): MovieResponse {
        return dataRepository.getMovies(sortBy,pageNo)
    }


}