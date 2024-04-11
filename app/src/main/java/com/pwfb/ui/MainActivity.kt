package com.pwfb.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pwfb.R
import com.pwfb.base.BaseActivity
import com.pwfb.databinding.ActivityMainBinding
import com.pwfb.theme.DataStoreTheme
import com.pwfb.ui.home.HomeFragment
import com.pwfb.ui.nutrition.NutritionFragment
import com.pwfb.ui.profile.ProfileFragment
import com.pwfb.ui.screen.NameScreen
import com.pwfb.ui.setting.NameActivity
import com.pwfb.ui.splash.SplashViewModel
import com.pwfb.ui.training.TrainingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(PWFB, "MainActivity onCreate")
        super.onCreate(savedInstanceState)

        setContent {
            DataStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isFirstInit = intent.getBooleanExtra("isFirstInit", false)
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = if(isFirstInit) "name" else "home"
                    ) {
                        composable(route = "name") { NameScreen(navController)}
                    }


                }
            }
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