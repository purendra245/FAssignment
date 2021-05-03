package com.fave.assignment.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fave.assignment.data.model.movies.MovieResponse
import com.fave.assignment.data.model.ResultData
import com.fave.assignment.data.model.detail.Movie
import com.fave.assignment.domain.paged.MoviePagingSource
import com.fave.assignment.domain.usecase.detail.GetMoviesDetailUseCase
import com.fave.assignment.domain.usecase.movie.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel

class MovieDetailViewModel
@Inject
constructor(val useCase: GetMoviesDetailUseCase): ViewModel() {

    fun getMoviesDetail(movieId:Int): LiveData<ResultData<Movie>> {
        return liveData {
            emit(ResultData.Loading())
            emit(useCase.getMoviesDetail(movieId))

        }
    }

}