package com.pritom.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.my.esheba.helper.GridSpacingItemDecoration
import com.pritom.assets.extension.getDateTime
import com.pritom.assets.extension.loadImage
import com.pritom.details.adapters.CastAdapter
import com.pritom.details.adapters.company.CompanyAdapter
import com.pritom.details.databinding.FragmentDetailsBinding
import com.pritom.dutta.movie.domain.utils.DataSourceConstants
import com.pritom.dutta.movie.domain.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null
    private val castAdapter: CastAdapter by lazy { CastAdapter() }
    private val productionCompanyAdapter: CompanyAdapter by lazy { CompanyAdapter() }
    private val viewModel: DetailsViewModel by viewModels()

    //    private val args: DetailsFragment by navArgs()
    private var movieName = ""
    private var movieDetails = ""
    private var posterUrl = ""
    private var movieID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { arg ->
            movieName = arg.getString("movie_name") ?: ""
            movieDetails = arg.getString("movie_details") ?: ""
            posterUrl = arg.getString("poster_img_url") ?: ""
            movieID = arg.getInt("movie_id", -1)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        initUI()
        event()
        updateData()
        return binding?.root
    }

    private fun initUI() {
        val gridCast = GridSpacingItemDecoration(4, 40, true)
        val gridProduction = GridSpacingItemDecoration(4, 40, true)
        binding?.rvCast?.apply {
            adapter = castAdapter
            addItemDecoration(gridCast)
        }

        binding?.rvProductionCompanies?.apply {
            adapter = productionCompanyAdapter
            addItemDecoration(gridProduction)
        }
    }

    private fun updateData() {
        binding?.apply {
            txtMovieTitle.text = movieName
            txtMovieTitleToolbar.text = movieName
            txtDescription.text = movieDetails
            appCompatImageView.loadImage(DataSourceConstants.BASE_WIDTH_780_PATH + posterUrl)
        }
        viewModel.getDetailsMovie(movieID)
        apiResponse()
    }

    private fun event() {
        binding?.btnImgBack?.setOnClickListener { findNavController().popBackStack() }
        binding?.btnImgBackToolbar?.setOnClickListener { findNavController().popBackStack() }
    }

    private fun apiResponse() {
        viewModel.moviesDetails.observe(viewLifecycleOwner) { data ->
            lifecycleScope.launch {
                data.details.collect { details ->
                    when (details) {
                        is NetworkResult.Error -> {
                            Toast.makeText(requireContext(), details.message, Toast.LENGTH_LONG)
                                .show()
                        }

                        is NetworkResult.Loading -> {}

                        is NetworkResult.Success -> {
                            details.data?.apply {
                                val rating = String.format("%.1f", voteAverage)
                                productionCompanyAdapter.submitList(productionCompanies)
                                binding?.apply {
                                    txtRating.text = "${rating}/10"
                                    txtLanguage.text = spokenLanguages[0].name
                                    txtRuntimePg.text = runtime.toString()
                                    txtReleaseDate.text =
                                        releaseDate?.getDateTime("yyyy-MM-dd", "MMM, dd yyyy")

                                    for (index in genres.indices) {
                                        val chip = LayoutInflater.from(context).inflate(
                                            R.layout.item_chip_category,
                                            chipCategory,
                                            false
                                        ) as Chip
                                        chip.text = genres[index].name.uppercase()
                                        // necessary to get single selection working
                                        chip.isClickable = false
                                        chip.isCheckable = false
                                        chipCategory.addView(chip)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            lifecycleScope.launch {
                data.cast.collect { castData ->
                    when (castData) {
                        is NetworkResult.Error -> {
                            Toast.makeText(requireContext(), castData.message, Toast.LENGTH_LONG)
                                .show()
                        }

                        is NetworkResult.Loading -> {}

                        is NetworkResult.Success -> {
                            castData.data?.apply {
                                castAdapter.submitList(cast)
                            }
                        }
                    }
                }
            }

        }
    }

}