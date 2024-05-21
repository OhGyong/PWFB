package com.pwfb.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.domain.usecase.PrefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val prefUseCase: PrefUseCase
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
            _firstInitObserve.value = prefUseCase.getFirstInit().first()
        }
    }
}