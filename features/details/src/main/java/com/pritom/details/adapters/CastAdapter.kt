package com.pritom.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pritom.assets.base_adapter.BaseListAdapter
import com.pritom.dutta.movie.domain.models.Movie

class CastAdapter() :
    BaseListAdapter<Movie>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = CastViewHolder(inflater, parent)

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CastViewHolder->{
                holder.bind()
            }
        }
    }
}