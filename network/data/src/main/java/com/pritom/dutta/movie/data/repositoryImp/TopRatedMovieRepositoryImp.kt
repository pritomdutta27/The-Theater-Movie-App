package com.pritom.dutta.movie.data.repositoryImp

import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.repository.TopRatedMovieRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Pritom Dutta on 11/5/24.
 */

class TopRatedMovieRepositoryImp @Inject constructor(): TopRatedMovieRepository {
    override suspend fun fetchTopRatedMovies(): Flow<NetworkResult<TmdbWrapperModel<Movie>>> {
        TODO("Not yet implemented")
    }
}