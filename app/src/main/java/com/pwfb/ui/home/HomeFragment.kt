package com.pwfb.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pwfb.R
import com.pwfb.base.BaseFragment
import com.pwfb.databinding.FragmentHomeBinding
import com.pwfb.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        viewModelObserve()
        viewModel.getWeight()
        viewModel.getDDay()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun viewModelObserve() {
        viewModel.weightObserve.observe(viewLifecycleOwner) {
            binding.tvWeight.text = it + getString(R.string.kg)
        }

        viewModel.dDayObserve.observe(viewLifecycleOwner) {
            println(it)
        }
    }
}