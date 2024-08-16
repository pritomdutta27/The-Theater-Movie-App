package com.pritom.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pritom.dutta.movie.data.FeedWrapper
import com.pritom.dutta.movie.data.SortType
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.MovieDetailsModel
import com.pritom.dutta.movie.domain.repository.movie.details.DetailsMovieRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: DetailsMovieRepository) :
    ViewModel() {

    private val _moviesDetails = MutableLiveData<NetworkResult<MovieDetailsModel>>()
    val moviesDetails: LiveData<NetworkResult<MovieDetailsModel>>
        get() = _moviesDetails

    fun getDetailsMovie(id: Int){
        viewModelScope.launch {
            repository.fetchDetailsMovies(id).collect { data ->
                _moviesDetails.postValue(data)
            }
        }
    }
}