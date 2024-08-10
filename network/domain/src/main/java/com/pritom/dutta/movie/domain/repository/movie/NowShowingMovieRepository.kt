package com.pritom.dutta.movie.domain.repository.movie

import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NowShowingMovieRepository {
    suspend fun fetchNowShowingMovies(): Flow<NetworkResult<TmdbWrapperModel<Movie>>>
}