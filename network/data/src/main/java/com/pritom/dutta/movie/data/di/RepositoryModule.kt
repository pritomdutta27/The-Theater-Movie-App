package com.pritom.dutta.movie.data.di

import com.pritom.dutta.movie.data.repositoryImp.TopRatedMovieRepositoryImp
import com.pritom.dutta.movie.domain.repository.TopRatedMovieRepository
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

}