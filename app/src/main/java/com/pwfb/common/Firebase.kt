package com.pwfb.common

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

object Firebase {
    val firebaseReference = Firebase.storage.reference
    val crashlytics = FirebaseCrashlytics.getInstance()
}