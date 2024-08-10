package com.pritom.tv_show.adpaters.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pritom.assets.base_adapter.BaseListAdapter
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TVShow

class PopularAdapter() :
    BaseListAdapter<TVShow>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = PopularViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PopularViewHolder->{
                holder.bind(getItem(position))
            }
        }
    }
}