package com.fave.assignment.di

import com.fave.assignment.data.datasource.derail.DetailRemoteDataSource
import com.fave.assignment.data.datasource.movie.MovieRemoteDatasource
import com.fave.assignment.data.repository.detail.MovieDetailRepositoryImpl
import com.fave.assignment.data.repository.movie.MovieRepositoryImpl
import com.fave.assignment.domain.repository.detail.MovieDetailRepository
import com.fave.assignment.domain.repository.movie.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun providesDataRepo(datasource: MovieRemoteDatasource): MovieRepository {
        return MovieRepositoryImpl(datasource)
    }

    @Provides
    fun providesDetailDataRepo(datasource: DetailRemoteDataSource): MovieDetailRepository {
        return MovieDetailRepositoryImpl(datasource)
    }
}