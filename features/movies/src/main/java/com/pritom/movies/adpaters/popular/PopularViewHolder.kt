package com.pritom.movies.adpaters.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.pritom.assets.base_adapter.BaseViewHolder
import com.pritom.movies.databinding.NowShowingItemBinding
import com.pritom.movies.databinding.PopularItemBinding

class PopularViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<PopularItemBinding>(
    binding = PopularItemBinding.inflate(inflater, parent, false)
) {
    fun bind(){
        itemView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}