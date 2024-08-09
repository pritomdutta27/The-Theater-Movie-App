package com.pritom.movies.adpaters.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pritom.assets.base_adapter.BaseListAdapter

class PopularAdapter() :
    BaseListAdapter<String>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = PopularViewHolder(inflater, parent)

    override fun getItemCount() = 4

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PopularViewHolder->{
                holder.bind()
            }
        }
    }
}