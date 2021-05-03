package com.fave.assignment.di


import com.fave.assignment.data.datasource.derail.DetailRemoteDataSource
import com.fave.assignment.data.datasource.movie.MovieRemoteDatasource
import com.fave.assignment.data.datasourceimpl.detail.DetailRemoteDataSourceImpl
import com.fave.assignment.data.datasourceimpl.movie.MovieRemoteDataSourceImpl
import com.fave.assignment.data.network.TMDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDatasource {
        return MovieRemoteDataSourceImpl(
            tmdbService
        )
    }

    @Singleton
    @Provides
    fun provideMovieDetailRemoteDataSource(tmdbService: TMDBService): DetailRemoteDataSource {
        return DetailRemoteDataSourceImpl(
            tmdbService
        )
    }
}