package com.pritom.dutta.movie.data.di

import com.pritom.dutta.movie.data.repositoryImp.movie.NowShowingMovieRepositoryImp
import com.pritom.dutta.movie.data.repositoryImp.movie.TopRatedMovieRepositoryImp
import com.pritom.dutta.movie.data.repositoryImp.movie.TrendingRepositoryImp
import com.pritom.dutta.movie.data.repositoryImp.tv_series.NowShowingTvSeriesRepositoryImp
import com.pritom.dutta.movie.data.repositoryImp.tv_series.TopRatedTvSeriesRepositoryImp
import com.pritom.dutta.movie.data.repositoryImp.tv_series.TrendingTvSeriesRepositoryImp
import com.pritom.dutta.movie.domain.repository.movie.NowShowingMovieRepository
import com.pritom.dutta.movie.domain.repository.movie.TopRatedMovieRepository
import com.pritom.dutta.movie.domain.repository.movie.TrendingRepository
import com.pritom.dutta.movie.domain.repository.tv_series.NowShowingTVRepository
import com.pritom.dutta.movie.domain.repository.tv_series.TopRatedTVRepository
import com.pritom.dutta.movie.domain.repository.tv_series.TrendingTvSeriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Pritom Dutta on 22/3/24.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideTopRatedMovieRepository(api: TopRatedMovieRepositoryImp): TopRatedMovieRepository

    @Binds
    @Singleton
    abstract fun provideNowShowingMovieRepository(api: NowShowingMovieRepositoryImp): NowShowingMovieRepository

    @Binds
    @Singleton
    abstract fun provideTrendingRepository(api: TrendingRepositoryImp): TrendingRepository


    //TODO: Tv series

    @Binds
    @Singleton
    abstract fun provideTopRatedTVRepository(api: TopRatedTvSeriesRepositoryImp): TopRatedTVRepository

    @Binds
    @Singleton
    abstract fun provideNowShowingTVRepository(api: NowShowingTvSeriesRepositoryImp): NowShowingTVRepository

    @Binds
    @Singleton
    abstract fun provideTrendingTvSeriesRepository(api: TrendingTvSeriesRepositoryImp): TrendingTvSeriesRepository

}