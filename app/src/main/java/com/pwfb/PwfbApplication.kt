package com.pwfb

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.google.firebase.messaging.messaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PwfbApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initFirebase()
    }

    private fun initFirebase() {
        Firebase.initialize(this)

        Firebase.messaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("LOG_TAG task.result: ${task.result}")
            }
        }
    }
}