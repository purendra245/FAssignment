package com.fave.assignment.domain.paged

import androidx.paging.PagingSource
import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.model.movies.Movies
import com.fave.assignment.domain.usecase.movie.GetMoviesUseCase
import javax.inject.Inject

class MoviePagingSource
(
    private val useCase: GetMoviesUseCase,private val sortBy: Constants.SortBy
) : PagingSource<Int, Movies>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {

        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = useCase.getMovies(sortBy,nextPage)
            print(movieListResponse)

            LoadResult.Page(
                data = movieListResponse.results!!,
                prevKey = if (nextPage == 1) null else nextPage - 1 ,
                nextKey = if (nextPage < movieListResponse.totalPages!!)
                    movieListResponse.page?.plus(1) else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}
