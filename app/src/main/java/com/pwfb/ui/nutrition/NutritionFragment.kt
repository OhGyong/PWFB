package com.pwfb.ui.nutrition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pwfb.base.BaseFragment
import com.pwfb.databinding.FragmentNutritionBinding

class NutritionFragment : BaseFragment() {

    private lateinit var binding: FragmentNutritionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNutritionBinding.inflate(layoutInflater)
        return binding.root
    }
}