package com.pritom.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.my.esheba.helper.GridSpacingItemDecoration
import com.pritom.assets.extension.loadImage
import com.pritom.details.adapters.CastAdapter
import com.pritom.details.adapters.company.CompanyAdapter
import com.pritom.details.databinding.FragmentDetailsBinding
import com.pritom.dutta.movie.domain.utils.DataSourceConstants
import com.pritom.dutta.movie.domain.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

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
        arguments?.let { arg->
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

    private fun updateData(){
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
           when(data){
               is NetworkResult.Error -> {Toast.makeText(requireContext(), data.message, Toast.LENGTH_LONG).show()}
               is NetworkResult.Loading -> {

               }
               is NetworkResult.Success -> {
                   data.data?.apply {
                       val rating = String.format("%.1f", voteAverage)
                       productionCompanyAdapter.submitList(productionCompanies)
                       binding?.apply {
                           txtRating.text = "${rating}/10"
                           txtLanguage.text = spokenLanguages[0].name
                       }
                   }
               }
           }
        }
    }

}