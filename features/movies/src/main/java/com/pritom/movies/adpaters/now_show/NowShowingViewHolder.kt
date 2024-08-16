package com.pritom.movies.adpaters.now_show

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pritom.assets.base_adapter.BaseViewHolder
import com.pritom.assets.extension.loadImage
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.utils.DataSourceConstants
import com.pritom.movies.databinding.NowShowingItemBinding

class NowShowingViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<NowShowingItemBinding>(
    binding = NowShowingItemBinding.inflate(inflater, parent, false)
) {
    fun bind(data: Movie, onClick: (data: Movie)->Unit){
        itemView.setOnClickListener {
            onClick(data)
        }
        binding.txtMovieTitle.text = data.title
        val rating = String.format("%.1f", data.voteAverage)
        binding.txtRating.text = "${rating}/10"
        binding.moviePosterImg.loadImage(DataSourceConstants.BASE_WIDTH_342_PATH + data.posterUrl)
    }
}