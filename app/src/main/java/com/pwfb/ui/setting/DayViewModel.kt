package com.pwfb.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.repository.PwfbPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayViewModel @Inject constructor(
    private val pwfbPreferencesRepository: PwfbPreferencesRepository
): ViewModel() {

    private val _dDayObserve: MutableLiveData<String> = MutableLiveData()
    val dDayObserve = _dDayObserve

    private val _firstInitObserve: MutableLiveData<Boolean> = MutableLiveData()
    val firstInitObserve = _firstInitObserve

    fun setDDay(weight: String) {
        viewModelScope.launch {
            dDayObserve.value = pwfbPreferencesRepository.setDDay(weight)
        }
    }

    fun setFirstInit() {
        viewModelScope.launch {
            firstInitObserve.value = pwfbPreferencesRepository.setFistInit()
        }
    }
}