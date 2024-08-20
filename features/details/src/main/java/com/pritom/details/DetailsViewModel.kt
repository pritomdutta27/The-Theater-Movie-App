package com.pritom.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pritom.dutta.movie.data.DetailWrapper
import com.pritom.dutta.movie.data.FeedWrapper
import com.pritom.dutta.movie.data.SortType
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.MovieDetailsModel
import com.pritom.dutta.movie.domain.models.cast.CastResponseModel
import com.pritom.dutta.movie.domain.repository.movie.actors.ActorRepository
import com.pritom.dutta.movie.domain.repository.movie.details.DetailsMovieRepository
import com.pritom.dutta.movie.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailsMovieRepository,
    private val actorRepository: ActorRepository,
) : ViewModel() {

    private val _moviesDetails = MutableLiveData<DetailWrapper>()
    val moviesDetails: LiveData<DetailWrapper>
        get() = _moviesDetails

    fun getDetailsMovie(id: Int) {
        viewModelScope.launch {
            val detailsDeferred: Deferred<Flow<NetworkResult<MovieDetailsModel>>>
            val actorsDeferred: Deferred<Flow<NetworkResult<CastResponseModel>>>
            coroutineScope {
                detailsDeferred = async { repository.fetchDetailsMovies(id) }
                actorsDeferred = async { actorRepository.fetchMovieActors(id) }
            }
            _moviesDetails.value = DetailWrapper(
                actorsDeferred.await(),
                detailsDeferred.await()
            )
        }
    }
}