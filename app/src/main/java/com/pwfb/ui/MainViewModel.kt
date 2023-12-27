package com.pwfb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.repository.PwfbPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pwfbPreferencesRepository: PwfbPreferencesRepository
): ViewModel() {

    private val _nameObserve: MutableLiveData<String> = MutableLiveData()
    val nameObserve = _nameObserve

    private val _dDayObserve: MutableLiveData<String> = MutableLiveData()
    val dDayObserve = _dDayObserve

    private val _weightObserve: MutableLiveData<String> = MutableLiveData()
    val weightObserve = _weightObserve

    private val _carbohydrateObserve: MutableLiveData<String> = MutableLiveData()
    val carbohydrateObserve = _carbohydrateObserve

    private val _fatObserve: MutableLiveData<String> = MutableLiveData()
    val fatObserve = _fatObserve

    private val _proteinObserve: MutableLiveData<String> = MutableLiveData()
    val proteinObserve = _proteinObserve

    private val _waterObserve: MutableLiveData<String> = MutableLiveData()
    val waterObserve = _waterObserve

    private val _sodiumObserve: MutableLiveData<String> = MutableLiveData()
    val sodiumObserve = _sodiumObserve

    private val _potassiumObserve: MutableLiveData<String> = MutableLiveData()
    val potassiumObserve = _potassiumObserve

    private val _creatineObserve: MutableLiveData<String> = MutableLiveData()
    val creatineObserve = _creatineObserve



    fun getName() {
        viewModelScope.launch {
            nameObserve.value = pwfbPreferencesRepository.getName().first()
        }
    }

    /**
     * 몸무게
     */
    fun getWeight() {
        viewModelScope.launch {
            weightObserve.value = pwfbPreferencesRepository.getWeight().first()
        }
    }

    /**
     * D-Day
     */
    fun getDDay() {
        viewModelScope.launch {
            dDayObserve.value = pwfbPreferencesRepository.getDDay().first()
        }
    }

    /**
     * 탄수화물
     */
    fun getCarbohydrate() {
        viewModelScope.launch {
            carbohydrateObserve.value = pwfbPreferencesRepository.getCarbohydrate().first()
        }
    }

    fun setCarbohydrate(carbohydrate: String) {
        viewModelScope.launch {
            carbohydrateObserve.value = pwfbPreferencesRepository.setCarbohydrate(carbohydrate)
        }
    }

    /**
     * 단백질
     */
    fun getProtein() {
        viewModelScope.launch {
            proteinObserve.value = pwfbPreferencesRepository.getProtein().first()
        }
    }

    fun setProtein(protein: String) {
        viewModelScope.launch {
            proteinObserve.value = pwfbPreferencesRepository.setProtein(protein)
        }
    }

    /**
     * 지방
     */
    fun getFat() {
        viewModelScope.launch {
            fatObserve.value = pwfbPreferencesRepository.getFat().first()
        }
    }

    fun setFat(fat: String) {
        viewModelScope.launch {
            fatObserve.value = pwfbPreferencesRepository.setFat(fat)
        }
    }

    /**
     * 수분
     */
    fun getWater() {
        viewModelScope.launch {
            waterObserve.value = pwfbPreferencesRepository.getWater().first()
        }
    }

    fun setWater(water: String) {
        viewModelScope.launch {
            waterObserve.value = pwfbPreferencesRepository.setWater(water)
        }
    }

    /**
     * 나트륨
     */
    fun getSodium() {
        viewModelScope.launch {
            sodiumObserve.value = pwfbPreferencesRepository.getSodium().first()
        }
    }

    fun setSodium(sodium: String) {
        viewModelScope.launch {
            sodiumObserve.value = pwfbPreferencesRepository.setSodium(sodium)
        }
    }

    /**
     * 칼륨
     */
    fun getPotassium() {
        viewModelScope.launch {
            potassiumObserve.value = pwfbPreferencesRepository.getPotassium().first()
        }
    }

    fun setPotassium(potassium: String) {
        viewModelScope.launch {
            potassiumObserve.value = pwfbPreferencesRepository.setPotassium(potassium)
        }
    }

    /**
     * 크레아틴
     */
    fun getCreatine() {
        viewModelScope.launch {
            creatineObserve.value = pwfbPreferencesRepository.getCreatine().first()
        }
    }

    fun setCreatine(creatine: String) {
        viewModelScope.launch {
            creatineObserve.value = pwfbPreferencesRepository.setCreatine(creatine)
        }
    }
}