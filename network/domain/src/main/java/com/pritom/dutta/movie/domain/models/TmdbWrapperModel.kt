package com.pritom.dutta.movie.domain.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Pritom Dutta on 10/5/24.
 */
data class TmdbWrapperModel<T>(
    val page: Int,
    @SerializedName("results")
    val items: List<T>,
    val total_results: Int,
    val total_pages: Int,
)