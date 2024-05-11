package com.pritom.dutta.movie.data.datasource.remote

import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.utils.DataSourceConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pritom Dutta on 10/5/24.
 */
interface MovieApiService {

    @GET("movie/popular?api_key=${DataSourceConstants.API_KEY}&language=en-US")
    suspend fun fetchTopRatedMovie(@Query("page") page: Int): Response<TmdbWrapperModel<Movie>>

//    @GET("movie/{id}?api_key=${DataSourceConstants.API_KEY}&language=en-US")
//    suspend fun fetchMovieDetails(@Path("id") id: Int): NetworkResult<MovieDetails>
}