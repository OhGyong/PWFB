package com.pwfb.presentation.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.usecase.NameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NameViewModel @Inject constructor(
    private val nameUseCase: NameUseCase
): ViewModel() {

    private val _nameObserve: MutableLiveData<PwfbResultEntity> = MutableLiveData()
    val nameObserve = _nameObserve

    fun setName(name: String) {
        viewModelScope.launch {
            _nameObserve.value = nameUseCase.setName(name)
        }
    }
}