package com.pritom.dutta.movie.data.repositoryImp.tv_series

import com.pritom.dutta.movie.data.datasource.remote.TvSeriesApiDataSource
import com.pritom.dutta.movie.data.utils.onException
import com.pritom.dutta.movie.domain.repository.tv_series.TopRatedTVRepository
import pritom.dutta.test.OpenForTesting
import javax.inject.Inject

/**
 * Created by Pritom Dutta on 11/5/24.
 */
@OpenForTesting
class TopRatedTvSeriesRepositoryImp @Inject constructor(private val api: TvSeriesApiDataSource) :
    TopRatedTVRepository {

    override suspend fun fetchTopRatedTvSeries() = api.invokePopular().onException()
}