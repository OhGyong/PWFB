package com.pwfb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.repository.PwfbPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pwfbPreferencesRepository: PwfbPreferencesRepository
): ViewModel() {

    private val _nameObserve: MutableLiveData<String> = MutableLiveData()
    val nameObserve = _nameObserve
    fun setName(name: String) {
        viewModelScope.launch {
            nameObserve.value = pwfbPreferencesRepository.setName(name)

        }
    }

    fun getName() {
        viewModelScope.launch {
            println("ViewModel ${pwfbPreferencesRepository.getName()}")
            val k = pwfbPreferencesRepository.getName().collect()
            println(k)
//            nameObserve.value = pwfbPreferencesRepository.getName()
        }
    }
}