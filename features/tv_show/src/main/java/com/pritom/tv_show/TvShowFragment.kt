package com.pritom.tv_show

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.my.esheba.helper.EqualSpacingItemDecoration
import com.pritom.dutta.movie.data.SortType
import com.pritom.dutta.movie.domain.utils.NetworkResult
import com.pritom.tv_show.adpaters.now_show.NowShowingAdapter
import com.pritom.tv_show.adpaters.popular.PopularAdapter
import com.pritom.tv_show.databinding.FragmentTvShowBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private var binding: FragmentTvShowBinding? = null
    private val viewModel: TvSeriesViewModel by viewModels()

    private val nowShowingAdapter: NowShowingAdapter by lazy {
        NowShowingAdapter {
            findNavController().navigate(R.id.action_tvShowFragment_to_detailsFragment)
        }
    }
    private val popularAdapter: PopularAdapter by lazy { PopularAdapter() }
    private val trendingAdapter: PopularAdapter by lazy { PopularAdapter() }
    private var myScrollViewerInstanceState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTvSeries()
    }

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
        binding?.shimmerFrameLayout?.isVisible = myScrollViewerInstanceState == null
        binding?.scrollTv?.isVisible = myScrollViewerInstanceState != null
        return binding?.root
    }

    private fun initUI() {

        binding?.rvNowShowing?.apply {
            addItemDecoration(EqualSpacingItemDecoration(0))
            adapter = nowShowingAdapter
            setHasFixedSize(true)
        }

        binding?.rvPopular?.apply {
            addItemDecoration(EqualSpacingItemDecoration(20))
            adapter = popularAdapter
            setHasFixedSize(true)
        }

        binding?.rvTrending?.apply {
            addItemDecoration(EqualSpacingItemDecoration(20))
            adapter = trendingAdapter
            setHasFixedSize(true)
        }
        apiResponse()
    }

    private fun apiResponse() {
        viewModel.tvSeries.observe(viewLifecycleOwner) { data ->
            data.onEach { movieRes ->
                lifecycleScope.launch {
                    movieRes.feeds.collect { response ->
                        when (response) {
                            is NetworkResult.Error -> {
                                Toast.makeText(
                                    requireContext(),
                                    response.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            is NetworkResult.Loading -> {
                                binding?.shimmerFrameLayout?.isVisible = true
                                binding?.scrollTv?.isVisible = false
                            }
                            is NetworkResult.Success -> {
                                withContext(Dispatchers.Main) {
                                    binding?.shimmerFrameLayout?.isVisible = false
                                    binding?.scrollTv?.isVisible = true
                                }
                                when (movieRes.sortType) {
                                    SortType.TRENDING -> {
                                        trendingAdapter.submitList(response.data?.items?.take(4))
                                    }

                                    SortType.MOST_POPULAR -> {
                                        popularAdapter.submitList(response.data?.items?.take(4))
                                    }

                                    SortType.HIGHEST_RATED -> {}
                                    SortType.UPCOMING -> {}
                                    SortType.NOW_PLAYING -> {
                                        nowShowingAdapter.submitList(response.data?.items)
                                    }

                                    SortType.DISCOVER -> {}
                                }
                            }
                        }
                    }
                }

            }
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