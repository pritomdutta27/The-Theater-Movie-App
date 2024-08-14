package com.pritom.movies

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.esheba.helper.EqualSpacingItemDecoration
import com.pritom.dutta.movie.data.SortType
import com.pritom.dutta.movie.domain.utils.NetworkResult
import com.pritom.movies.adpaters.now_show.NowShowingAdapter
import com.pritom.movies.adpaters.popular.PopularAdapter
import com.pritom.movies.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var binding: FragmentMovieBinding? = null
    private val viewModel: MovieViewModel by viewModels()

    private val nowShowingAdapter: NowShowingAdapter by lazy {
        NowShowingAdapter {
            findNavController().navigate(R.id.action_movieFragment_to_detailsFragment)
        }
    }
    private val popularAdapter: PopularAdapter by lazy { PopularAdapter() }
    private val trendingAdapter: PopularAdapter by lazy { PopularAdapter() }
    private var myScrollViewerInstanceState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovies()
    }
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
        binding?.shimmerFrameLayout?.isVisible = myScrollViewerInstanceState == null
        binding?.movieScrollView?.isVisible = myScrollViewerInstanceState != null

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

    private fun initUI() {

//        nowShowingAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
//        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        linearLayoutManager.onRestoreInstanceState()
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
        viewModel.movies.observe(viewLifecycleOwner) { data ->
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
                                binding?.movieScrollView?.isVisible = false
                            }

                            is NetworkResult.Success -> {
                                withContext(Dispatchers.Main) {
                                    binding?.shimmerFrameLayout?.isVisible = false
                                    binding?.movieScrollView?.isVisible = true
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
}