package com.pwfb.ui.nutrition

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pwfb.R
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

            setTextDDay(dDay.toInt())
        }
    }

    private fun setTextDDay(dDay: Int) {
        when(dDay) {
            -7 -> {
                binding.tvCarbohydrate.text = getString(R.string.nutrition_carbohydrate_day_7)
                binding.tvProtein.text = getString(R.string.nutrition_carbohydrate_day_7)
                binding.tvFat.text = getString(R.string.nutrition_fat_day_7654)
                binding.tvWater.text = getString(R.string.nutrition_usually_intake)
                binding.tvSodium.text = getString(R.string.nutrition_usually_intake)
                binding.tvPotassium.text = getString(R.string.nutrition_usually_intake)
                binding.tvCreatine.text = getString(R.string.nutrition_creatine_day_7654)
                binding.tvDietaryFiber.text = getString(R.string.nutrition_usually_intake)
            }
            -6 -> {
                binding.tvCarbohydrate.text = getString(R.string.nutrition_carbohydrate_day_654)
                binding.tvProtein.text = getString(R.string.nutrition_protein_day_654)
                binding.tvFat.text = getString(R.string.nutrition_fat_day_7654)
                binding.tvWater.text = getString(R.string.nutrition_usually_intake)
                binding.tvSodium.text = getString(R.string.nutrition_usually_intake)
                binding.tvPotassium.text = getString(R.string.nutrition_usually_intake)
                binding.tvCreatine.text = getString(R.string.nutrition_creatine_day_7654)
                binding.tvDietaryFiber.text = getString(R.string.nutrition_dietary_fiber_day_654)
            }
            -5 -> {
                binding.tvCarbohydrate.text = getString(R.string.nutrition_carbohydrate_day_654)
                binding.tvProtein.text = getString(R.string.nutrition_protein_day_654)
                binding.tvFat.text = getString(R.string.nutrition_fat_day_7654)
                binding.tvWater.text = getString(R.string.nutrition_usually_intake)
                binding.tvSodium.text = getString(R.string.nutrition_usually_intake)
                binding.tvPotassium.text = getString(R.string.nutrition_usually_intake)
                binding.tvCreatine.text = getString(R.string.nutrition_creatine_day_7654)
                binding.tvDietaryFiber.text = getString(R.string.nutrition_dietary_fiber_day_654)
            }
            -4 -> {
                binding.tvCarbohydrate.text = getString(R.string.nutrition_carbohydrate_day_654)
                binding.tvProtein.text = getString(R.string.nutrition_protein_day_654)
                binding.tvFat.text = getString(R.string.nutrition_fat_day_7654)
                binding.tvWater.text = getString(R.string.nutrition_usually_intake)
                binding.tvSodium.text = getString(R.string.nutrition_usually_intake)
                binding.tvPotassium.text = getString(R.string.nutrition_usually_intake)
                binding.tvCreatine.text = getString(R.string.nutrition_creatine_day_7654)
                binding.tvDietaryFiber.text = getString(R.string.nutrition_dietary_fiber_day_654)
            }
            -3 -> {
                binding.tvCarbohydrate.text = getString(R.string.nutrition_carbohydrate_day_3)
                binding.tvProtein.text = getString(R.string.nutrition_protein_day_32)
                binding.tvFat.text = getString(R.string.nutrition_fat_day_32)
                binding.tvWater.text = getString(R.string.nutrition_water_day_3)
                binding.tvSodium.text = getString(R.string.nutrition_sodium_day_3)
                binding.tvPotassium.text = getString(R.string.nutrition_potassium_day_3)
                binding.tvCreatine.text = getString(R.string.nutrition_creatine_day_321)
                binding.tvDietaryFiber.text = getString(R.string.nutrition_dietary_fiber_day_321)
            }
            -2 -> {
                binding.tvCarbohydrate.text = getString(R.string.nutrition_carbohydrate_day_2)
                binding.tvProtein.text = getString(R.string.nutrition_protein_day_32)
                binding.tvFat.text = getString(R.string.nutrition_fat_day_32)
                binding.tvWater.text = getString(R.string.nutrition_water_day_2)
                binding.tvSodium.text = getString(R.string.nutrition_sodium_day_2)
                binding.tvPotassium.text = getString(R.string.nutrition_usually_intake)
                binding.tvCreatine.text = getString(R.string.nutrition_creatine_day_321)
                binding.tvDietaryFiber.text = getString(R.string.nutrition_dietary_fiber_day_321)
            }
            -1 -> {
                binding.tvCarbohydrate.text = getString(R.string.nutrition_carbohydrate_day_1)
                binding.tvProtein.text = getString(R.string.nutrition_protein_day_1)
                binding.tvFat.text = getString(R.string.nutrition_fat_day_1)
                binding.tvWater.text = getString(R.string.nutrition_water_day_1)
                binding.tvSodium.text = getString(R.string.nutrition_sodium_day_1)
                binding.tvPotassium.text = getString(R.string.nutrition_usually_intake)
                binding.tvCreatine.text = getString(R.string.nutrition_creatine_day_321)
                binding.tvDietaryFiber.text = getString(R.string.nutrition_dietary_fiber_day_321)
            }
        }
    }
}