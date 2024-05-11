package com.pritom.dutta.movie.data.datasource.remote

import com.pritom.dutta.movie.data.utils.onResponse
import javax.inject.Inject

/**
 * Created by Pritom Dutta on 10/5/24.
 */
class ApiRemoteDataSource @Inject constructor(private val api: MovieApiService) {
    suspend operator fun invoke(page: Int = 1) = api.fetchTopRatedMovie(page).onResponse()
}