package com.dogactanriverdi.humanity.presentation.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.CycleInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dogactanriverdi.humanity.R
import com.dogactanriverdi.humanity.common.viewBinding
import com.dogactanriverdi.humanity.databinding.FragmentHomeBinding
import com.dogactanriverdi.humanity.presentation.EgoSwitchState
import com.dogactanriverdi.humanity.presentation.OtherSwitchesState
import com.dogactanriverdi.humanity.presentation.SharedViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            with(viewModel) {

                switchEgo.setOnCheckedChangeListener { _, isChecked ->
                    toggleEgoSwitch(isChecked)
                }

                switchGiving.setOnCheckedChangeListener { _, isChecked ->
                    if (switchEgo.isChecked) {
                        shakeScreen()
                        switchGiving.isChecked = false
                    } else {
                        toggleOtherSwitches(0, isChecked)
                    }
                }

                switchHappiness.setOnCheckedChangeListener { _, isChecked ->
                    if (switchEgo.isChecked) {
                        shakeScreen()
                        switchHappiness.isChecked = false
                    } else {
                        toggleOtherSwitches(1, isChecked)
                    }
                }

                switchKindness.setOnCheckedChangeListener { _, isChecked ->
                    if (switchEgo.isChecked) {
                        shakeScreen()
                        switchKindness.isChecked = false
                    } else {
                        toggleOtherSwitches(2, isChecked)
                    }
                }

                switchOptimism.setOnCheckedChangeListener { _, isChecked ->
                    if (switchEgo.isChecked) {
                        shakeScreen()
                        switchOptimism.isChecked = false
                    } else {
                        toggleOtherSwitches(3, isChecked)
                    }
                }

                switchRespect.setOnCheckedChangeListener { _, isChecked ->
                    if (switchEgo.isChecked) {
                        shakeScreen()
                        switchRespect.isChecked = false
                    } else {
                        toggleOtherSwitches(4, isChecked)
                    }
                }

                observeEgoSwitchState(egoSwitch)
                observeOtherSwitchesState(otherSwitchesStates)
            }
        }
    }

    private fun shakeScreen() {
        val animator = ObjectAnimator.ofFloat(
            binding.root,
            "translationX",
            0f,
            25f,
            -25f,
            25f,
            -25f,
            15f,
            -15f,
            6f,
            -6f,
            0f
        )
        animator.duration = 500
        animator.interpolator = CycleInterpolator(0.3f)
        animator.start()
    }

    private fun observeEgoSwitchState(state: StateFlow<EgoSwitchState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { egoSwitchState ->
                binding.switchEgo.isChecked = egoSwitchState.isChecked
            }
        }
    }

    private fun observeOtherSwitchesState(state: StateFlow<OtherSwitchesState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { otherSwitchesState ->
                with(binding) {
                    val isOtherSwitchesChecked = otherSwitchesState.isOtherSwitchesChecked
                    switchGiving.isChecked = isOtherSwitchesChecked[0]
                    switchHappiness.isChecked = isOtherSwitchesChecked[1]
                    switchKindness.isChecked = isOtherSwitchesChecked[2]
                    switchOptimism.isChecked = isOtherSwitchesChecked[3]
                    switchRespect.isChecked = isOtherSwitchesChecked[4]
                }
            }
        }
    }
}