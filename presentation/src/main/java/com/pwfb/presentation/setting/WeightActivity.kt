package com.pwfb.presentation.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.pwfb.presentation.R
import com.pwfb.presentation.base.BaseActivity
import com.pwfb.presentation.common.DataStoreResult.RESULT_SUCCESS
import com.pwfb.presentation.databinding.ActivityWeightBinding
import com.pwfb.domain.entity.PwfbResultEntity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeightActivity: BaseActivity() {

    private lateinit var binding: ActivityWeightBinding
    private val viewModel: WeightViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(PWFB, "WeightActivity onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivityWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * 이름 입력 시 버튼 색 변경
         */
        binding.etWeight.addTextChangedListener {
            if(binding.etWeight.text.isEmpty()) {
                binding.btNext.isEnabled = false
                binding.btNext.setTextColor(getColor(R.color.c_949292))
            }else {
                binding.btNext.isEnabled = true
                binding.btNext.setTextColor(getColor(R.color.white))
            }
        }

        /**
         * 배경 클릭 시 키보드 내리기
         */
        binding.clName.setOnClickListener {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.etWeight.windowToken, 0)
        }

        /**
         * Next 버튼 클릭
         */
        binding.btNext.setOnClickListener {
            viewModel.setWeight(binding.etWeight.text.toString())
        }

        viewModel.weightObserve.observe(this) {
            if(it == PwfbResultEntity.Success(RESULT_SUCCESS)) {
                val intent = Intent(applicationContext, DayActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }
        }
    }
}