package com.pwfb.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pwfb.R
import com.pwfb.base.BaseFragment
import com.pwfb.common.NutritionObject.nutritionList
import com.pwfb.databinding.FragmentHomeBinding
import com.pwfb.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    private var kg = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        viewModelObserve()
        viewModel.getWeight()


        return binding.root
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun viewModelObserve() {
        viewModel.weightObserve.observe(viewLifecycleOwner) {
            @SuppressLint("SetTextI18n")
            binding.tvWeight.text = it + getString(R.string.kg)
            kg = it.toDouble()
            viewModel.getDDay()
        }

        viewModel.dDayObserve.observe(viewLifecycleOwner) {
            val datePref = it.substring(0..9)
            val targetDate = SimpleDateFormat("yyyy.MM.dd").parse(datePref)!!.time
            val today = Calendar.getInstance().time.time

            val dDay = (today - targetDate) / (60*60*24*1000)

            binding.tvDDay.text = "D$dDay"
            binding.tvDate.text = it

            binding.lvNutrition.adapter = ListAdapter(requireContext(), nutritionList, kg, dDay.toInt())

            binding.tvTraining.text = when(dDay.toInt()) {
                -6 -> {
                    getString(R.string.training_day_6)
                }
                -5, -4 -> {
                    getString(R.string.training_day_54)
                }
                -3 -> {
                    getString(R.string.training_day_3)
                }
                -2 -> {
                    getString(R.string.training_day_2)
                }
                -1 -> {
                    getString(R.string.training_day_1)
                }
                else -> {
                    getString(R.string.nutrition_usually_intake)
                }
            }
        }
    }
}