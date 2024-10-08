package com.pritom.tv_show.adpaters.now_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pritom.assets.base_adapter.BaseListAdapter
import com.pritom.dutta.movie.domain.models.Movie
import com.pritom.dutta.movie.domain.models.TVShow

class NowShowingAdapter(private val onClick: () -> Unit) :
    BaseListAdapter<TVShow>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = NowShowingViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NowShowingViewHolder->{
                holder.bind(getItem(position), onClick)
            }
        }
    }
}