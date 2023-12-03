package com.pwfb.ui.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pwfb.base.BaseFragment
import com.pwfb.databinding.FragmentTrainingBinding

class TrainingFragment : BaseFragment() {

    private lateinit var binding: FragmentTrainingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(layoutInflater)
        return binding.root
    }
}