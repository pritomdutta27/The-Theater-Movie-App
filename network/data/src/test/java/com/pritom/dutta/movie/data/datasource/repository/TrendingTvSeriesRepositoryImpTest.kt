package com.pritom.dutta.movie.data.datasource.repository

import com.pritom.dutta.movie.data.helper.Helper
import com.pritom.dutta.movie.data.repositoryImp.movie.TrendingRepositoryImp
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrendingTvSeriesRepositoryImpTest {
    @Mock
    lateinit var sutApi: TrendingRepositoryImp

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getTopRatedMovies_EmptyList() = runTest {
        emptyList()
        val response = sutApi.fetchTrendingMovies().first()
        assertEquals(true, response is NetworkResult.Success)
        assertEquals(0, response.data?.items?.size)
    }

    @Test
    fun getTopRatedMovies_4_Items() = runTest {
        success()
        val response = sutApi.fetchTrendingMovies().first()
        assertEquals(true, response is NetworkResult.Success)
        assertEquals(4, response.data?.items?.size)
    }

    @Test
    fun getTopRatedMovies_get_error() = runTest {
        failure()
        val response = sutApi.fetchTrendingMovies().first()
        assertEquals(true, response is NetworkResult.Error)
        assertEquals(401, response.code)
        assertEquals("Unauthorized", response.message)
    }

    private suspend fun success() {
        val testData = Helper.getTestData("api_response_data.json")
        Mockito.`when`(sutApi.fetchTrendingMovies()).thenReturn(
            flow { emit(NetworkResult.Success(testData)) }
        )
    }

    private suspend fun failure() {
        Mockito.`when`(sutApi.fetchTrendingMovies()).thenReturn(
            flow { emit(NetworkResult.Error(401,"Unauthorized")) }
        )
    }

    private suspend fun emptyList() {
        val testData = Helper.getTestData("movie_empty_list.json")
        Mockito.`when`(sutApi.fetchTrendingMovies()).thenReturn(
            flow { emit(NetworkResult.Success(testData)) }
        )
    }
}