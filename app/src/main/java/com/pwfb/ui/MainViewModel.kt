package com.pwfb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pwfb.common.DataStoreResult
import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.usecase.NameUseCase
import com.pwfb.domain.usecase.PrefUseCase
import com.pwfb.domain.usecase.file.CreateLogFileUseCase
import com.pwfb.domain.usecase.file.GetLogFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefUseCase: PrefUseCase,
    private val nameUseCase: NameUseCase,
    private val createLogFileUseCase: CreateLogFileUseCase,
    private val getLogFileUseCase: GetLogFileUseCase
): ViewModel() {

    private val _nameObserve: MutableStateFlow<String> = MutableStateFlow("")
    val nameObserve = _nameObserve.asStateFlow()

    private val _weightObserve: MutableStateFlow<String> = MutableStateFlow("")
    val weightObserve = _weightObserve

    private val _dDayObserve: MutableStateFlow<String> = MutableStateFlow("")
    val dDayObserve = _dDayObserve

    private val _trainingProgramObserve: MutableStateFlow<String> = MutableStateFlow("")
    val trainingProgramObserve = _trainingProgramObserve

    private val _carbohydrateObserve: MutableStateFlow<String> = MutableStateFlow("")
    val carbohydrateObserve = _carbohydrateObserve

    private val _fatObserve: MutableStateFlow<String> = MutableStateFlow("")
    val fatObserve = _fatObserve

    private val _proteinObserve: MutableStateFlow<String> = MutableStateFlow("")
    val proteinObserve = _proteinObserve

    private val _waterObserve: MutableStateFlow<String> = MutableStateFlow("")
    val waterObserve = _waterObserve

    private val _sodiumObserve: MutableStateFlow<String> = MutableStateFlow("")
    val sodiumObserve = _sodiumObserve

    private val _potassiumObserve: MutableStateFlow<String> = MutableStateFlow("")
    val potassiumObserve = _potassiumObserve

    private val _creatineObserve: MutableStateFlow<String> = MutableStateFlow("")
    val creatineObserve = _creatineObserve

    private val _dietaryFiber: MutableStateFlow<String> = MutableStateFlow("")
    val dietaryFiber = _dietaryFiber

    var logFile: MutableLiveData<File?> = MutableLiveData()
        private set

    fun setUploadFile() {
        logFile.value = getLogFileUseCase()
    }

    fun getName() {
        viewModelScope.launch {
            nameUseCase().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _nameObserve.emit(it)
            }
        }
    }

    /**
     * 몸무게
     */
    fun getWeight() {
        viewModelScope.launch {
            prefUseCase.getWeight().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _weightObserve.emit(it)
            }
        }
    }

    fun setWeight(weight: String) {
        viewModelScope.launch {
            when(val setWeightResult = prefUseCase.setWeight(weight)) {
                is PwfbResultEntity.Success -> {
                    _weightObserve.emit(DataStoreResult.SET_WEIGHT)
                }
                else -> {
                    createLogFileUseCase.invoke("[몸무게 설정 실패]: $setWeightResult")
                }
            }
        }
    }

    /**
     * D-Day
     */
    fun getDDay() {
        viewModelScope.launch {
            prefUseCase.getDDay().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _dDayObserve.emit(it)
            }
        }
    }

    fun setDDay(dDay: String) {
        viewModelScope.launch {
            when(prefUseCase.setDDay(dDay)) {
                is PwfbResultEntity.Success -> {
                    _dDayObserve.emit(DataStoreResult.SET_D_DAY)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    /**
     * 트레이닝
     */
    fun getTrainingProgram() {
        viewModelScope.launch {
            prefUseCase.getTrainingProgram().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _trainingProgramObserve.emit(it)
            }
        }
    }
    fun setTrainingProgram(trainingProgram: String) {
        viewModelScope.launch {
            when(prefUseCase.setTrainingProgram(trainingProgram)) {
                is PwfbResultEntity.Success -> {
                    _trainingProgramObserve.emit(DataStoreResult.SET_TRAINING_PROGRAM)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    /**
     * 탄수화물
     */
    fun getCarbohydrate() {
        viewModelScope.launch {
            prefUseCase.getCarbohydrate().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _carbohydrateObserve.emit(it)
            }
        }
    }

    fun setCarbohydrate(carbohydrate: String) {
        viewModelScope.launch {
            when(prefUseCase.setCarbohydrate(carbohydrate)) {
                is PwfbResultEntity.Success -> {
                    _carbohydrateObserve.emit(DataStoreResult.SET_CARBOHYDRATE)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    /**
     * 단백질
     */
    fun getProtein() {
        viewModelScope.launch {
            prefUseCase.getProtein().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _proteinObserve.emit(it)
            }
        }
    }

    fun setProtein(protein: String) {
        viewModelScope.launch {
            when(prefUseCase.setProtein(protein)) {
                is PwfbResultEntity.Success -> {
                    _proteinObserve.emit(DataStoreResult.SET_PROTEIN)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    /**
     * 지방
     */
    fun getFat() {
        viewModelScope.launch {
            prefUseCase.getFat().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _fatObserve.emit(it)
            }
        }
    }

    fun setFat(fat: String) {
        viewModelScope.launch {
            when(prefUseCase.setFat(fat)) {
                is PwfbResultEntity.Success -> {
                    _fatObserve.emit(DataStoreResult.SET_FAT)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    /**
     * 수분
     */
    fun getWater() {
        viewModelScope.launch {
            prefUseCase.getWater().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _waterObserve.emit(it)
            }
        }
    }

    fun setWater(water: String) {
        viewModelScope.launch {
            when(prefUseCase.setWater(water)) {
                is PwfbResultEntity.Success -> {
                    _waterObserve.emit(DataStoreResult.SET_WATER)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    /**
     * 나트륨
     */
    fun getSodium() {
        viewModelScope.launch {
            prefUseCase.getSodium().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _sodiumObserve.emit(it)
            }
        }
    }

    fun setSodium(sodium: String) {
        viewModelScope.launch {
            when(prefUseCase.setSodium(sodium)) {
                is PwfbResultEntity.Success -> {
                    _sodiumObserve.emit(DataStoreResult.SET_SODIUM)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    /**
     * 칼륨
     */
    fun getPotassium() {
        viewModelScope.launch {
            prefUseCase.getPotassium().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _potassiumObserve.emit(it)
            }
        }
    }

    fun setPotassium(potassium: String) {
        viewModelScope.launch {
            when(prefUseCase.setPotassium(potassium)) {
                is PwfbResultEntity.Success -> {
                    _potassiumObserve.emit(DataStoreResult.SET_POTASSIUM)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    /**
     * 크레아틴
     */
    fun getCreatine() {
        viewModelScope.launch {
            prefUseCase.getCreatine().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _creatineObserve.emit(it)
            }
        }
    }

    fun setCreatine(creatine: String) {
        viewModelScope.launch {
            when(prefUseCase.setCreatine(creatine)) {
                is PwfbResultEntity.Success -> {
                    _creatineObserve.emit(DataStoreResult.SET_CREATINE)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }

    /**
     * 식이 섬유
     */
    fun getDietaryFiber() {
        viewModelScope.launch {
            prefUseCase.getDietaryFiber().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ""
            ).collectLatest {
                _dietaryFiber.emit(it)
            }
        }
    }

    fun setDietaryFiber(dietaryFiber: String) {
        viewModelScope.launch {
            when(prefUseCase.setDietaryFiber(dietaryFiber)) {
                is PwfbResultEntity.Success -> {
                    _dietaryFiber.emit(DataStoreResult.SET_DIETARY_FIBER)
                }
                else -> {
                    // todo Failure
                }
            }
        }
    }
}