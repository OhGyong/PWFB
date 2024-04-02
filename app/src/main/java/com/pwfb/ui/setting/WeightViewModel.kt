package com.pwfb.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.data.PwfbPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeightViewModel @Inject constructor(
    private val pwfbPreferencesRepository: PwfbPreferencesRepository
): ViewModel() {

    private val _weightObserve: MutableLiveData<String> = MutableLiveData()
    val weightObserve = _weightObserve

    fun setWeight(weight: String) {
        viewModelScope.launch {
            weightObserve.value = pwfbPreferencesRepository.setWeight(weight)

        }
    }
}