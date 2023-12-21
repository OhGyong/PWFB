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
import java.text.SimpleDateFormat
import java.util.Calendar


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

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun viewModelObserve() {
        viewModel.weightObserve.observe(viewLifecycleOwner) {
            @SuppressLint("SetTextI18n")
            binding.tvWeight.text = it + getString(R.string.kg)
        }

        viewModel.dDayObserve.observe(viewLifecycleOwner) {
            val datePref = it.substring(0..9)
            val targetDate = SimpleDateFormat("yyyy.MM.dd").parse(datePref)!!.time
            val today = Calendar.getInstance().time.time

            val dDay = (today - targetDate) / (60*60*24*1000)

            binding.tvDDay.text = "D$dDay"
            binding.tvDate.text = it
        }
    }
}