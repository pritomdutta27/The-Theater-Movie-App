package com.pritom.dutta.movie.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pritom.dutta.movie.data.datasource.remote.MovieApiService
import com.pritom.dutta.movie.data.datasource.remote.TvSeriesService
import com.pritom.dutta.movie.data.di.annotations.AppBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Pritom Dutta on 11/5/24.
 */
@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Provides
    @Singleton
    @AppBaseUrl
    fun provideRetrofit(
        @AppBaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()
    }

    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideApiService(@AppBaseUrl retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTvSeriesService(@AppBaseUrl retrofit: Retrofit): TvSeriesService {
        return retrofit.create(TvSeriesService::class.java)
    }
}