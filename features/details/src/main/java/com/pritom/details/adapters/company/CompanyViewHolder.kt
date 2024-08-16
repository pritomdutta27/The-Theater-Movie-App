package com.pritom.details.adapters.company

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pritom.assets.base_adapter.BaseViewHolder
import com.pritom.assets.extension.loadImage
import com.pritom.details.databinding.ProductionCompanyItemBinding
import com.pritom.dutta.movie.domain.models.ProductionCompany
import com.pritom.dutta.movie.domain.utils.DataSourceConstants

class CompanyViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<ProductionCompanyItemBinding>(
    binding = ProductionCompanyItemBinding.inflate(inflater, parent, false)
) {

    fun bind(data: ProductionCompany) {

//        itemView.layoutParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
        data.apply {
            binding.apply {
                txtCompanyName.text = data.name
                imgCompany.loadImage(DataSourceConstants.BASE_WIDTH_342_PATH + data.logo_path)
            }
        }
    }
}