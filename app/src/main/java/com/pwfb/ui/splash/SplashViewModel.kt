package com.pwfb.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.repository.PwfbPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val pwfbPreferencesRepository: PwfbPreferencesRepository
): ViewModel() {
    private var _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading

    private val _firstInitObserve: MutableLiveData<Boolean> = MutableLiveData()
    val firstInitObserve = _firstInitObserve

    init {
        viewModelScope.launch {
            delay(0L)
            _isLoading.value = false
        }
    }

     fun getFirstInit() {
        viewModelScope.launch {
            firstInitObserve.value = pwfbPreferencesRepository.getFirstInit().first()
        }
    }
}