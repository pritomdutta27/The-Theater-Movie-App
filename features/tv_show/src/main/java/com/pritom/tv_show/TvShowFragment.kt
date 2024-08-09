package com.pritom.tv_show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.my.esheba.helper.EqualSpacingItemDecoration
import com.pritom.tv_show.adpaters.now_show.NowShowingAdapter
import com.pritom.tv_show.adpaters.popular.PopularAdapter
import com.pritom.tv_show.databinding.FragmentTvShowBinding


class TvShowFragment : Fragment() {

    private var binding: FragmentTvShowBinding? = null
    private val nowShowingAdapter: NowShowingAdapter by lazy { NowShowingAdapter() }
    private val popularAdapter: PopularAdapter by lazy { PopularAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        initUI()
        return binding?.root
    }

    private fun initUI(){

        binding?.rvNowShowing?.apply {
            addItemDecoration(EqualSpacingItemDecoration(0))
            adapter = nowShowingAdapter
        }

        binding?.rvPopular?.apply {
            addItemDecoration(EqualSpacingItemDecoration(20))
            adapter = popularAdapter
        }

        binding?.rvTrending?.apply {
            addItemDecoration(EqualSpacingItemDecoration(20))
            adapter = popularAdapter
        }
    }

}