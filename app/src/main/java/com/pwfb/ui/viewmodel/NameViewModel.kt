package com.pwfb.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.common.DataStoreResult
import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.usecase.NameUseCase
import com.pwfb.ui.state.NameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NameViewModel @Inject constructor(
    private val nameUseCase: NameUseCase
): ViewModel() {

    var nameObserve by mutableStateOf("")
        private set


    fun setName(name: String) {
        viewModelScope.launch {
            when(nameUseCase.setName(name)) {
                is PwfbResultEntity.Success -> {
                    nameObserve = DataStoreResult.SET_NAME
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }
}