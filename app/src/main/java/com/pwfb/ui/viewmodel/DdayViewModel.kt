package com.pwfb.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.pwfb.common.DataStoreResult
import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.usecase.DdayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class DdayViewModel @Inject constructor(
    private val dDayUseCase: DdayUseCase
): ViewModel() {

    var dDayState by mutableStateOf("")
        private set

    private val _firstInitObserve: MutableLiveData<PwfbResultEntity> = MutableLiveData()
    val firstInitObserve = _firstInitObserve

    var dDayTimeLiveData : MutableLiveData<String> = MutableLiveData("")
        private set

    init {
        setCurrentTime()
    }

    fun setDDay(dDay: String) {
        viewModelScope.launch {
            when(dDayUseCase.setDday(dDay)) {
                is PwfbResultEntity.Success -> {
                    dDayState = DataStoreResult.SET_D_DAY
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    fun setFirstInit() {
        viewModelScope.launch {
            _firstInitObserve.value = dDayUseCase.setFistInit()
        }
    }

    fun setDate(date: CalendarDay): String {
        val month = if(date.month<10) "0${date.month}" else "${date.month}"
        val day = if(date.day<10) "0${date.day}" else "${date.day}"
        val week = when(LocalDate.of(date.year, date.month, date.day).dayOfWeek.value) {
            1 -> "(월)"
            2 -> "(화)"
            3 -> "(수)"
            4 -> "(목)"
            5 -> "(금)"
            6 -> "(토)"
            7 -> "(일)"
            else -> ""
        }
        return "${date.year}.$month.$day$week"
    }

    fun setCurrentTime(): String {
        val localTime = LocalDateTime.now().toLocalTime()
        return updateTime(localTime)
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