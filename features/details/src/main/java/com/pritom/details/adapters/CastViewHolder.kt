package com.pritom.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pritom.assets.base_adapter.BaseViewHolder
import com.pritom.assets.extension.loadImage
import com.pritom.details.databinding.CastItemBinding
import com.pritom.dutta.movie.domain.models.cast.Cast
import com.pritom.dutta.movie.domain.utils.DataSourceConstants

class CastViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<CastItemBinding>(
    binding = CastItemBinding.inflate(inflater, parent, false)
) {

    fun bind(data: Cast){
        data.apply {
            binding.apply {
                txtCastName.text = data.name
                imgCast.loadImage(DataSourceConstants.BASE_WIDTH_342_PATH + data.profilePath)
            }
        }
    }
}