package com.pritom.movies

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.esheba.helper.EqualSpacingItemDecoration
import com.pritom.movies.adpaters.now_show.NowShowingAdapter
import com.pritom.movies.adpaters.popular.PopularAdapter
import com.pritom.movies.databinding.FragmentMovieBinding


class MovieFragment : Fragment() {

    private var binding: FragmentMovieBinding? = null
    private val nowShowingAdapter: NowShowingAdapter by lazy { NowShowingAdapter() }
    private val popularAdapter: PopularAdapter by lazy { PopularAdapter() }
    private var myScrollViewerInstanceState: Parcelable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        initUI()
        //save state for scroll view
        if (myScrollViewerInstanceState != null) {
            binding?.movieScrollView?.onRestoreInstanceState(myScrollViewerInstanceState)
        }
        return binding?.root
    }

    override fun onPause() {
        super.onPause()
        myScrollViewerInstanceState = binding?.movieScrollView?.onSaveInstanceState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myScrollViewerInstanceState = null
    }

    private fun initUI(){

//        nowShowingAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
//        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        linearLayoutManager.onRestoreInstanceState()
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