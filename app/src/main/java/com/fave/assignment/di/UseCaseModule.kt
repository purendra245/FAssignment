package com.fave.assignment.di


import com.fave.assignment.domain.repository.detail.MovieDetailRepository
import com.fave.assignment.domain.repository.movie.MovieRepository
import com.fave.assignment.domain.usecase.detail.GetMoviesDetailUseCase
import com.fave.assignment.domain.usecase.movie.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun providesUseCase(dataRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(dataRepository)
    }

    @Provides
    fun providesMovieUseCase(dataDetailRepository: MovieDetailRepository): GetMoviesDetailUseCase {
        return GetMoviesDetailUseCase(dataDetailRepository)
    }
}