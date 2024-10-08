package com.pwfb.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.ads.AdRequest
import com.pwfb.R
import com.pwfb.base.BaseFragment
import com.pwfb.common.DataStoreResult
import com.pwfb.common.NutritionObject.nutritionEntityLists
import com.pwfb.databinding.FragmentHomeBinding
import com.pwfb.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

        binding.adView.loadAd(AdRequest.Builder().build())

        return binding.root
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun viewModelObserve() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.weightObserve.collectLatest {
                        if(it == DataStoreResult.SET_WEIGHT){
                            // todo
                            return@collectLatest
                        }
                        binding.tvWeight.text = it + getString(R.string.kg)
                        kg = it.toDouble()
                        viewModel.getDDay()
                    }
                }

                launch {
                    viewModel.dDayObserve.collectLatest {
                        if(it == DataStoreResult.SET_D_DAY) {
                            // todo
                            return@collectLatest
                        }
                        if(it.isEmpty()) {
                            // todo
                            return@collectLatest
                        }
                        val datePref = it.substring(0..9)
                        val targetDate = SimpleDateFormat("yyyy.MM.dd").parse(datePref)!!.time
                        val today = Calendar.getInstance().time.time

                        val dDay = (today - targetDate) / (60*60*24*1000)

                        binding.tvDDay.text = "D$dDay"
                        binding.tvDate.text = it

                        binding.lvNutrition.adapter = ListAdapter(requireContext(), nutritionEntityLists, kg, dDay.toInt())

                        binding.tvTraining.text = when(dDay.toInt()) {
                            -6 -> {
                                getString(R.string.home_training_day_6)
                            }
                            -5, -4 -> {
                                getString(R.string.home_training_day_54)
                            }
                            -3 -> {
                                getString(R.string.home_training_day_3)
                            }
                            -2 -> {
                                getString(R.string.home_training_day_2)
                            }
                            -1 -> {
                                getString(R.string.home_training_day_1)
                            }
                            else -> {
                                getString(R.string.nutrition_usually_intake, "💪")
                            }
                        }

                        binding.tvPeakWeekStrategy.text = when(dDay.toInt()) {
                            -7 -> {
                                getString(R.string.re_feed_day)
                            }
                            -6, -5, -4 -> {
                                getString(R.string.low_carbohydrate_day)
                            }
                            -3, -2 -> {
                                getString(R.string.high_carbohydrate_day)
                            }
                            -1 -> {
                                getString(R.string.correction_day)
                            }
                            0 -> {
                                getString(R.string.man_of_the_hour)
                            }
                            else -> {
                                getString(R.string.gonna_be_alright)
                            }
                        }
                    }
                }
            }
        }
    }
}