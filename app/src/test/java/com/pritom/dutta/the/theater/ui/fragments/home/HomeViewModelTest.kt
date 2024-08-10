package com.pritom.dutta.the.theater.ui.fragments.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pritom.dutta.movie.domain.repository.movie.TopRatedMovieRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import com.pritom.dutta.the.theater.utils.Helper
import com.pritom.dutta.the.theater.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Pritom Dutta on 11/5/24.
 */
class HomeViewModelTest{

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: TopRatedMovieRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }
    @Test
    fun getTopRatedMovies_empty() =  runTest{
        emptyList()

        val sut = HomeViewModel(repository)
        sut.getTopRatedMovies()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.topMovies.getOrAwaitValue()

        assertEquals(true, result is NetworkResult.Success)
        assertEquals(0, result.data?.items?.size)
    }

    @Test
    fun getTopRatedMovies_4_list() =  runTest{
        success()

        val sut = HomeViewModel(repository)
        sut.getTopRatedMovies()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.topMovies.getOrAwaitValue()

        assertEquals(true, result is NetworkResult.Success)
        assertEquals(4, result.data?.items?.size)
    }

    @Test
    fun getTopRatedMovies_4_error() =  runTest{
        failure()

        val sut = HomeViewModel(repository)
        sut.getTopRatedMovies()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.topMovies.getOrAwaitValue()

        assertEquals(true, result is NetworkResult.Error)
        assertEquals(401, result.code)
        assertEquals("Unauthorized", result.message)
    }

    private suspend fun success() {
        val testData = Helper.getTestData("api_response_data.json")
        Mockito.`when`(repository.fetchTopRatedMovies()).thenReturn(
            flow { emit(NetworkResult.Success(testData)) }
        )
    }

    private suspend fun failure() {
        Mockito.`when`(repository.fetchTopRatedMovies()).thenReturn(
            flow { emit(NetworkResult.Error(401,"Unauthorized")) }
        )
    }

    private suspend fun emptyList() {
        val testData = Helper.getTestData("movie_empty_list.json")
        Mockito.`when`(repository.fetchTopRatedMovies()).thenReturn(
            flow { emit(NetworkResult.Success(testData)) }
        )
    }

}