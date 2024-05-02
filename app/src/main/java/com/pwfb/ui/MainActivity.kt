package com.pwfb.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pwfb.R
import com.pwfb.base.BaseActivity
import com.pwfb.common.ScreenName.SCREEN_DAY
import com.pwfb.common.ScreenName.SCREEN_HOME
import com.pwfb.common.ScreenName.SCREEN_NAME
import com.pwfb.common.ScreenName.SCREEN_WEIGHT
import com.pwfb.theme.DataStoreTheme
import com.pwfb.ui.screen.DdayScreen
import com.pwfb.ui.screen.HomeScreen
import com.pwfb.ui.screen.NameScreen
import com.pwfb.ui.screen.WeightScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
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

                    val drawableList: List<Drawable?> = listOf(
                        ResourcesCompat.getDrawable(
                            this.resources,
                            R.drawable.shape_calendar_today,
                            null
                        ),
                        ResourcesCompat.getDrawable(
                            this.resources,
                            R.drawable.selector_calendar_day,
                            null
                        )
                    )

                    NavHost(
                        navController = navController,
                        startDestination = if(isFirstInit) SCREEN_NAME else SCREEN_HOME
                    ) {
                        composable(route = SCREEN_NAME) { NameScreen(navController) }
                        composable(route = SCREEN_WEIGHT) { WeightScreen(navController) }
                        composable(route = SCREEN_DAY) { DdayScreen(navController, drawableList) }
                        composable(route = SCREEN_HOME) { HomeScreen(navController) }
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