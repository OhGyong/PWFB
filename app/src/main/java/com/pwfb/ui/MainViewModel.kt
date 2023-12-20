package com.pwfb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.repository.PwfbPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pwfbPreferencesRepository: PwfbPreferencesRepository
): ViewModel() {

    private val _nameObserve: MutableLiveData<String> = MutableLiveData()
    val nameObserve = _nameObserve

    private val _weightObserve: MutableLiveData<String> = MutableLiveData()
    val weightObserve = _weightObserve

    private val _dDayObserve: MutableLiveData<String> = MutableLiveData()
    val dDayObserve = _dDayObserve

    fun getName() {
        viewModelScope.launch {
            nameObserve.value = pwfbPreferencesRepository.getName().first()
        }
    }

    fun getWeight() {
        viewModelScope.launch {
            weightObserve.value = pwfbPreferencesRepository.getWeight().first()
        }
    }

    fun getDDay() {
        viewModelScope.launch {
            dDayObserve.value = pwfbPreferencesRepository.getDDay().first()
        }
    }
}