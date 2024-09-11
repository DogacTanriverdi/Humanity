package com.dogactanriverdi.humanity.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val _egoSwitch = MutableStateFlow(EgoSwitchState())
    val egoSwitch: StateFlow<EgoSwitchState> = _egoSwitch

    private val _otherSwitchesState = MutableStateFlow(OtherSwitchesState())
    val otherSwitchesStates: StateFlow<OtherSwitchesState> get() = _otherSwitchesState

    fun toggleEgoSwitch(isChecked: Boolean) {
        viewModelScope.launch {
            _egoSwitch.value = egoSwitch.value.copy(isChecked = isChecked)
            if (isChecked) {
                _otherSwitchesState.value = otherSwitchesStates.value.copy(
                    isOtherSwitchesChecked = listOf(false, false, false, false, false)
                )
            }
        }
    }

    fun toggleOtherSwitches(index: Int, isChecked: Boolean) {
        viewModelScope.launch {
            val updatedList = otherSwitchesStates.value.isOtherSwitchesChecked.toMutableList()
            _otherSwitchesState.value = otherSwitchesStates.value.copy(
                isOtherSwitchesChecked = updatedList.also {
                    it[index] = isChecked
                }
            )
        }
    }
}

data class EgoSwitchState(
    val isChecked: Boolean = true
)

data class OtherSwitchesState(
    val isOtherSwitchesChecked: List<Boolean> = listOf(false, false, false, false, false)
)