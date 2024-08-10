package com.pritom.tv_show.adpaters.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.pritom.assets.base_adapter.BaseViewHolder
import com.pritom.assets.extension.loadImage
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TVShow
import com.pritom.dutta.movie.domain.utils.DataSourceConstants
import com.pritom.tv_show.databinding.PopularItemBinding

class PopularViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<PopularItemBinding>(
    binding = PopularItemBinding.inflate(inflater, parent, false)
) {
    fun bind(data: TVShow){

        itemView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        binding.txtMovieTitle.text = data.title
        val rating = String.format("%.1f", data.voteAverage)
        binding.txtRating.text = "${rating}/10"
        binding.txtDuration.text = data.releaseDate
        binding.moviePosterImg.loadImage(DataSourceConstants.BASE_WIDTH_342_PATH + data.posterUrl)
    }
}