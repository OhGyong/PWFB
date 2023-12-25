package com.pwfb.ui.nutrition

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pwfb.base.BaseFragment
import com.pwfb.databinding.FragmentNutritionBinding
import com.pwfb.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar


@AndroidEntryPoint
class NutritionFragment : BaseFragment() {

    private lateinit var binding: FragmentNutritionBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNutritionBinding.inflate(layoutInflater)

        viewModelObserve()
        viewModel.getDDay()

        return binding.root
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun viewModelObserve() {
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