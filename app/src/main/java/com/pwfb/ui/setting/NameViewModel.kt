package com.pwfb.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.data.PwfbPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NameViewModel @Inject constructor(
    private val pwfbPreferencesRepository: PwfbPreferencesRepository
): ViewModel() {

    private val _nameObserve: MutableLiveData<String> = MutableLiveData()
    val nameObserve = _nameObserve

    fun setName(name: String) {
        viewModelScope.launch {
            nameObserve.value = pwfbPreferencesRepository.setName(name)

        }
    }
}