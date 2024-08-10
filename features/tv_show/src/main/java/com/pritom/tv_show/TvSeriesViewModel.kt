package com.pritom.tv_show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pritom.dutta.movie.data.FeedWrapper
import com.pritom.dutta.movie.data.SortType
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TVShow
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.repository.tv_series.NowShowingTVRepository
import com.pritom.dutta.movie.domain.repository.tv_series.TopRatedTVRepository
import com.pritom.dutta.movie.domain.repository.tv_series.TrendingTvSeriesRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesViewModel @Inject constructor(
    private val repository: TopRatedTVRepository,
    private val repositoryNowShowing: NowShowingTVRepository,
    private val repositoryTrending: TrendingTvSeriesRepository,
) : ViewModel()  {

    private val _tvSeries = MutableLiveData<List<FeedWrapper<TVShow>>>()
    val tvSeries: LiveData<List<FeedWrapper<TVShow>>>
        get() = _tvSeries

    fun getTvSeries() {
        viewModelScope.launch {

            val nowPlayingDeferred: Deferred<Flow<NetworkResult<TmdbWrapperModel<TVShow>>>>
            val popularDeferred: Deferred<Flow<NetworkResult<TmdbWrapperModel<TVShow>>>>
            val trendingDeferred: Deferred<Flow<NetworkResult<TmdbWrapperModel<TVShow>>>>

            coroutineScope {
                nowPlayingDeferred = async { repositoryNowShowing.fetchNowShowingTV() }
                popularDeferred = async { repository.fetchTopRatedTvSeries() }
                trendingDeferred = async { repositoryTrending.fetchTrendingTvSeries() }
            }

            _tvSeries.postValue(listOf(
                FeedWrapper(nowPlayingDeferred.await(), R.string.on_the_air, SortType.NOW_PLAYING),
                FeedWrapper(popularDeferred.await(), R.string.tv_popular, SortType.MOST_POPULAR),
                FeedWrapper(trendingDeferred.await(), R.string.tv_trending, SortType.TRENDING),
            ))
        }
    }
}