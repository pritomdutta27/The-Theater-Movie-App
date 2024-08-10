package com.pritom.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pritom.dutta.movie.data.FeedWrapper
import com.pritom.dutta.movie.data.SortType
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.repository.movie.NowShowingMovieRepository
import com.pritom.dutta.movie.domain.repository.movie.TopRatedMovieRepository
import com.pritom.dutta.movie.domain.repository.movie.TrendingRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: TopRatedMovieRepository,
    private val repositoryNowShowing: NowShowingMovieRepository,
    private val repositoryTrending: TrendingRepository,
) : ViewModel()  {

    private val _movies = MutableLiveData<List<FeedWrapper<Movie>>>()
    val movies: LiveData<List<FeedWrapper<Movie>>>
        get() = _movies

    fun getMovies() {
        viewModelScope.launch {

            val nowPlayingDeferred: Deferred<Flow<NetworkResult<TmdbWrapperModel<Movie>>>>
            val popularDeferred: Deferred<Flow<NetworkResult<TmdbWrapperModel<Movie>>>>
            val trendingDeferred: Deferred<Flow<NetworkResult<TmdbWrapperModel<Movie>>>>

            coroutineScope {
                nowPlayingDeferred = async { repositoryNowShowing.fetchNowShowingMovies() }
                popularDeferred = async { repository.fetchTopRatedMovies() }
                trendingDeferred = async { repositoryTrending.fetchTrendingMovies() }
            }

            _movies.postValue(listOf(
                FeedWrapper(nowPlayingDeferred.await(), R.string.now_showing, SortType.NOW_PLAYING),
                FeedWrapper(popularDeferred.await(), R.string.popular, SortType.MOST_POPULAR),
                FeedWrapper(trendingDeferred.await(), R.string.trending, SortType.TRENDING),
            ))
        }
    }
}