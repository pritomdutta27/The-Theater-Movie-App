package com.pritom.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.my.esheba.helper.GridSpacingItemDecoration
import com.pritom.details.adapters.CastAdapter
import com.pritom.details.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null
    private val castAdapter: CastAdapter by lazy { CastAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        initUI()
        event()
        return binding?.root
    }

    private fun initUI() {
        val gridDecoration = GridSpacingItemDecoration(4, 40, true)
        binding?.rvCast?.apply {
            adapter = castAdapter
            addItemDecoration(gridDecoration)
        }
    }

    private fun event() {
        binding?.btnImgBack?.setOnClickListener { findNavController().popBackStack() }
        binding?.btnImgBackToolbar?.setOnClickListener { findNavController().popBackStack() }
    }

}