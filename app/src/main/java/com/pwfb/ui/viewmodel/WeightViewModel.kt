package com.pwfb.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.common.DataStoreResult
import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.usecase.WeightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeightViewModel @Inject constructor(
    private val weightUseCase: WeightUseCase
): ViewModel() {

    var weightObserve by mutableStateOf("")
        private set


    fun setWeight(weight: String) {
        viewModelScope.launch {
            when(weightUseCase.setWeight(weight)) {
                is PwfbResultEntity.Success -> {
                    weightObserve = DataStoreResult.SET_WEIGHT
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }
}