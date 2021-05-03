package com.fave.assignment.data.datasourceimpl.movie

import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.customException.NoInternetException
import com.fave.assignment.data.customException.NotFoundException
import com.fave.assignment.data.customException.UnAuthorizedException
import com.fave.assignment.data.customException.UnKnownException
import com.fave.assignment.data.datasource.movie.MovieRemoteDatasource
import com.fave.assignment.data.model.movies.MovieResponse
import com.fave.assignment.data.model.ResultData
import com.fave.assignment.data.network.TMDBService
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class MovieRemoteDataSourceImpl
@Inject
constructor(
    private val tmdbService: TMDBService
): MovieRemoteDatasource {
    override suspend fun getMovies(sortBy: Constants.SortBy, page: Int): ResultData<MovieResponse> {

        val response: Response<MovieResponse>
        try {
             response = tmdbService.getMovies(sortBy.value, page)
            if (response.isSuccessful){
                if (response.body() != null){
                    return ResultData.Success(response.body()!!)
                }
            }
            else{
                val errorBody = response.errorBody()
                return if (errorBody != null){
                    ResultData.Error(mapApiException(response.code()))
                } else ResultData.Error(mapApiException(0))
            }
        }catch (t: Throwable){
            t.printStackTrace()
            return ResultData.Error(mapToNetworkError(t))
        }

        return ResultData.Error(HttpException(response))
    }



    private fun mapApiException(code: Int): Exception {
        return when(code){
            HttpURLConnection.HTTP_NOT_FOUND -> NotFoundException()
            HttpURLConnection.HTTP_UNAUTHORIZED -> UnAuthorizedException()
            else -> UnKnownException()
        }
    }

    private fun mapToNetworkError(t: Throwable): Exception {
        return when(t){
            is SocketTimeoutException
            -> SocketTimeoutException("Connection Timed Out")
            is UnknownHostException
            -> NoInternetException()
            else
            -> UnKnownException()

        }
    }
}