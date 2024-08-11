package com.pritom.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pritom.assets.base_adapter.BaseViewHolder
import com.pritom.details.databinding.CastItemBinding

class CastViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<CastItemBinding>(
    binding = CastItemBinding.inflate(inflater, parent, false)
) {

    fun bind(){

//        itemView.layoutParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
    }
}