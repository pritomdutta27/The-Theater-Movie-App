package com.pritom.dutta.movie.data.datasource.remote

import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.utils.DataSourceConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvSeriesService {

    @GET("tv/popular?api_key=${DataSourceConstants.API_KEY}&language=en-US")
    suspend fun fetchTopRatedTvSeries(@Query("page") page: Int): Response<TmdbWrapperModel<Movie>>

    @GET("tv/now_playing?api_key=${DataSourceConstants.API_KEY}&language=en-US")
    suspend fun fetchNowShowingTvSeries(@Query("page") page: Int): Response<TmdbWrapperModel<Movie>>

    @GET("trending/tv/day?api_key=${DataSourceConstants.API_KEY}&language=en-US")
    suspend fun fetchTrendingMTvSeries(@Query("page") page: Int): Response<TmdbWrapperModel<Movie>>
}