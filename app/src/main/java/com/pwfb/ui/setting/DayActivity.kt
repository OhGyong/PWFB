package com.pwfb.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.pwfb.R
import com.pwfb.base.BaseActivity
import com.pwfb.common.DataStoreResult
import com.pwfb.databinding.ActivityDayBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DayActivity : BaseActivity() {

    private lateinit var binding: ActivityDayBinding
    private val viewModel: DayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(PWFB, "WeightActivity onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
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
            println(binding.etWeight.text.toString())
            viewModel.setWeight(binding.etWeight.text.toString())
        }

        viewModel.weightObserve.observe(this) {
            if(it == DataStoreResult.SET_NAME) {
                val intent = Intent(applicationContext, WeightActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }
        }
    }
}