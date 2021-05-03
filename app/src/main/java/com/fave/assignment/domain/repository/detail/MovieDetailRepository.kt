package com.fave.assignment.domain.repository.detail

import com.fave.assignment.data.model.detail.Movie


interface MovieDetailRepository {
    suspend fun getMoviesDetail(movieId:Int): Movie
}