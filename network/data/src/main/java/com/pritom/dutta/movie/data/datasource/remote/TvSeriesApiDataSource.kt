package com.pritom.dutta.movie.data.datasource.remote

import com.pritom.dutta.movie.data.utils.onResponse
import pritom.dutta.test.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class TvSeriesApiDataSource @Inject constructor(private val api: MovieApiService) {

    suspend fun invokePopular(page: Int = 1) = api.fetchTopRatedMovie(page).onResponse()

    suspend fun invokeNowShowing(page: Int = 1) = api.fetchNowShowingMovie(page).onResponse()

    suspend fun invokeTrending(page: Int = 1) = api.fetchTrendingMovie(page).onResponse()
}