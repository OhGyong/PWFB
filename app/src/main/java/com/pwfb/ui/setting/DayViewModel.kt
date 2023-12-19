package com.pwfb.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.repository.PwfbPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayViewModel @Inject constructor(
    private val pwfbPreferencesRepository: PwfbPreferencesRepository
): ViewModel() {

    private val _dDayObserve: MutableLiveData<String> = MutableLiveData()
    val dDayObserve = _dDayObserve

    fun setDDay(weight: String) {
        viewModelScope.launch {
            dDayObserve.value = pwfbPreferencesRepository.setDDay(weight)
        }
    }
}