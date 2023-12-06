package com.pwfb.ui.setting

import android.os.Bundle
import android.util.Log
import com.pwfb.base.BaseActivity
import com.pwfb.databinding.ActivityNameBinding

class NameActivity: BaseActivity() {

    private lateinit var binding: ActivityNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(PWFB, "NameActivity onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}