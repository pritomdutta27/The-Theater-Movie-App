package com.pritom.tv_show

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.my.esheba.helper.EqualSpacingItemDecoration
import com.pritom.tv_show.adpaters.now_show.NowShowingAdapter
import com.pritom.tv_show.adpaters.popular.PopularAdapter
import com.pritom.tv_show.databinding.FragmentTvShowBinding


class TvShowFragment : Fragment() {

    private var binding: FragmentTvShowBinding? = null
    private val nowShowingAdapter: NowShowingAdapter by lazy { NowShowingAdapter() }
    private val popularAdapter: PopularAdapter by lazy { PopularAdapter() }
    private var myScrollViewerInstanceState: Parcelable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        initUI()
        //save state for scroll view
        if (myScrollViewerInstanceState != null) {
            binding?.scrollTv?.onRestoreInstanceState(myScrollViewerInstanceState)
        }
        return binding?.root
    }

    private fun initUI() {

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

    override fun onPause() {
        super.onPause()
        myScrollViewerInstanceState = binding?.scrollTv?.onSaveInstanceState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myScrollViewerInstanceState = null
    }

}