package com.fave.assignment.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.model.movies.Movies
import com.fave.assignment.domain.paged.MoviePagingSource
import com.fave.assignment.domain.usecase.movie.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel

class MainViewModel
@Inject
constructor(val useCase: GetMoviesUseCase): ViewModel() {

    fun fetchMovieData(sortBy: Constants.SortBy): Flow<PagingData<Movies>> {
        return  Pager(PagingConfig(20)) {
            MoviePagingSource(useCase,sortBy)
        }.flow.cachedIn(viewModelScope)
    }

}