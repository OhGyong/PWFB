package com.pwfb.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.usecase.WeightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeightViewModel @Inject constructor(
    private val weightUseCase: WeightUseCase
): ViewModel() {

    private val _weightObserve: MutableLiveData<PwfbResultEntity> = MutableLiveData()
    val weightObserve = _weightObserve

    fun setWeight(weight: String) {
        viewModelScope.launch {
            _weightObserve.value = weightUseCase.setWeight(weight)

        }
    }
}