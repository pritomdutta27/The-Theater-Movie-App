package com.pritom.dutta.movie.data.repositoryImp.tv_series

import com.pritom.dutta.movie.data.datasource.remote.TvSeriesApiDataSource
import com.pritom.dutta.movie.data.utils.onException
import com.pritom.dutta.movie.domain.repository.tv_series.TrendingTvSeriesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import pritom.dutta.test.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class TrendingTvSeriesRepositoryImp @Inject constructor(private val api: TvSeriesApiDataSource) :
    TrendingTvSeriesRepository {

    override suspend fun fetchTrendingTvSeries() = api.invokeTrending().onException().flowOn(IO)
}