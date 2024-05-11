package com.pritom.dutta.movie.data.repositoryImp

import com.pritom.dutta.movie.data.datasource.remote.ApiRemoteDataSource
import com.pritom.dutta.movie.data.utils.onException
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.repository.TopRatedMovieRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import pritom.dutta.test.OpenForTesting
import javax.inject.Inject

/**
 * Created by Pritom Dutta on 11/5/24.
 */
@OpenForTesting
class TopRatedMovieRepositoryImp @Inject constructor(private val api: ApiRemoteDataSource) :
    TopRatedMovieRepository {
    override suspend fun fetchTopRatedMovies() = api.invoke().onException()
}