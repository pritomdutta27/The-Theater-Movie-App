package com.pritom.dutta.movie.data

import android.os.Parcelable
import androidx.annotation.StringRes
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TmdbWrapperModel
import com.pritom.dutta.movie.domain.utils.NetworkResult
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.Flow


@Parcelize
enum class SortType : Parcelable {
    TRENDING,
    MOST_POPULAR,
    HIGHEST_RATED,
    UPCOMING,
    NOW_PLAYING,
    DISCOVER
}

class FeedWrapper(
    val feeds: Flow<NetworkResult<TmdbWrapperModel<Movie>>>,
    @StringRes val sortTypeResourceId: Int,
    val sortType: SortType
)