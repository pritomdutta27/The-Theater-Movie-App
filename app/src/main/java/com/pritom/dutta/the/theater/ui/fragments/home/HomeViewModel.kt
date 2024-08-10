package com.pritom.dutta.the.theater.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.repository.movie.TopRatedMovieRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Pritom Dutta on 11/5/24.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: TopRatedMovieRepository) :
    ViewModel() {

    private val _topMovies = MutableLiveData<NetworkResult<TmdbWrapperModel<Movie>>>()
    val topMovies: LiveData<NetworkResult<TmdbWrapperModel<Movie>>>
        get() = _topMovies

    fun getTopRatedMovies() {
        viewModelScope.launch {
            repository.fetchTopRatedMovies().collect { data ->
                _topMovies.postValue(data)
            }
        }
    }

}