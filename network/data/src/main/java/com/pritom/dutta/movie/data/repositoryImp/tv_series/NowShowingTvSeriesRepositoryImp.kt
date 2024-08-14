package com.pritom.dutta.movie.data.repositoryImp.tv_series

import com.pritom.dutta.movie.data.datasource.remote.TvSeriesApiDataSource
import com.pritom.dutta.movie.data.utils.onException
import com.pritom.dutta.movie.domain.repository.tv_series.NowShowingTVRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import pritom.dutta.test.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class NowShowingTvSeriesRepositoryImp @Inject constructor(private val api: TvSeriesApiDataSource) :
    NowShowingTVRepository {

    override suspend fun fetchNowShowingTV() = api.invokeNowShowing().onException().flowOn(IO)
}