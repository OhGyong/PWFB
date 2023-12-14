package com.pwfb.ui.setting

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.pwfb.base.BaseActivity
import com.pwfb.databinding.ActivityNameBinding
import com.pwfb.databinding.ActivityWeightBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeightActivity: BaseActivity() {

    private lateinit var binding: ActivityWeightBinding
    private val viewModel: NameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(PWFB, "WeightActivity onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivityWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

}