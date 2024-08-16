package com.pritom.details.adapters.company

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pritom.assets.base_adapter.BaseListAdapter
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.ProductionCompany

class CompanyAdapter() :
    BaseListAdapter<ProductionCompany>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = CompanyViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CompanyViewHolder->{
                holder.bind(getItem(position))
            }
        }
    }
}