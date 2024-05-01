package com.pwfb.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.usecase.PrefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class DdayViewModel @Inject constructor(
    private val prefUseCase: PrefUseCase
): ViewModel() {

    private val _dDayObserve: MutableLiveData<PwfbResultEntity> = MutableLiveData()
    val dDayObserve = _dDayObserve

    private val _firstInitObserve: MutableLiveData<PwfbResultEntity> = MutableLiveData()
    val firstInitObserve = _firstInitObserve

    var dDayTimeLiveData : MutableLiveData<String> = MutableLiveData("")
        private set

    init {
        setCurrentTime()
    }


    fun setDDay(weight: String) {
        viewModelScope.launch {
            _dDayObserve.value = prefUseCase.setDDay(weight)
        }
    }

    fun setFirstInit() {
        viewModelScope.launch {
            _firstInitObserve.value = prefUseCase.setFistInit()
        }
    }

    private fun setCurrentTime() {
        val localTime = LocalDateTime.now().toLocalTime()
        dDayTimeLiveData.value = updateTime(localTime)
    }

    fun updateTime(snappedTime: LocalTime): String {
        val amPm = if(snappedTime.hour < 12) "오전" else "오후"

        val hour = if(amPm == "오후") {
            if((snappedTime.hour - 12)<10) "0${(snappedTime.hour - 12)}" else "${(snappedTime.hour - 12)}"
        } else {
            if(snappedTime.hour < 10) "0${snappedTime.hour}" else "${snappedTime.hour}"
        }

        val minute = if(snappedTime.minute < 10) "0${snappedTime.minute}" else "${snappedTime.minute}"

        return  "$amPm $hour:$minute"
    }
}