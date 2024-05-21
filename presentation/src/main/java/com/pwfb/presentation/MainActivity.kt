package com.pwfb.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.pwfb.presentation.base.BaseActivity
import com.pwfb.presentation.databinding.ActivityMainBinding
import com.pwfb.presentation.home.HomeFragment
import com.pwfb.presentation.nutrition.NutritionFragment
import com.pwfb.presentation.profile.ProfileFragment
import com.pwfb.presentation.training.TrainingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(PWFB, "MainActivity onCreate")

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainBnv.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.item_navi_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fcv, HomeFragment()).commit()
                }
                R.id.item_navi_nutrition -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fcv, NutritionFragment()).commit()
                }
                R.id.item_navi_training -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fcv, TrainingFragment()).commit()
                }
                R.id.item_navi_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fcv, ProfileFragment()).commit()
                }
            }
            true
        }
    }

    /**
     * 키보드 내리기
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        if(currentFocus is EditText) {
            currentFocus!!.clearFocus()
        }

        return super.dispatchTouchEvent(ev)
    }
}