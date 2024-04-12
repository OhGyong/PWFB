package com.pwfb.ui

import android.content.Context
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pwfb.base.BaseActivity
import com.pwfb.theme.DataStoreTheme
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

                    NavHost(
                        navController = navController,
                        startDestination = if(isFirstInit) "name" else "home"
                    ) {
                        composable(route = "name") { NameScreen(navController)}
                        composable(route = "weight") { WeightScreen(navController) }
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