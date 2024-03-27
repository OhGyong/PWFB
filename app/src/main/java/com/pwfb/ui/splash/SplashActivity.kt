package com.pwfb.ui.splash

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pwfb.R
import com.pwfb.base.BaseActivity
import com.pwfb.ui.MainActivity
import com.pwfb.ui.setting.NameActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity: BaseActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(PWFB, "SplashActivity onCreate")

        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 다크모드 비활성화
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        /**
         * 스플래시 탐지
         */
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (!viewModel.isLoading.value) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )

        /**
         * 스플래시 끝나고 애니메이션 적용
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                ).run {
                    interpolator = AnticipateInterpolator()
                    duration = 0L
                    doOnEnd { splashScreenView.remove() }
                    start()
                    addListener(object : Animator.AnimatorListener{
                        override fun onAnimationStart(p0: Animator) {
                        }
                        override fun onAnimationEnd(p0: Animator) {
                            viewModel.getFirstInit()
                        }
                        override fun onAnimationCancel(p0: Animator) {
                        }
                        override fun onAnimationRepeat(p0: Animator) {
                        }
                    })
                }
            }
        } else {
            viewModel.getFirstInit()
        }

        viewModel.firstInitObserve.observe(this) {
            runBlocking { delay(2000) }

            // 앱 첫 진입으로 판단 -> 설정 화면 이동
            val intent = if(it == true) {
                Intent(applicationContext, NameActivity::class.java)
            }
            else {
                Intent(applicationContext, MainActivity::class.java)
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }
}