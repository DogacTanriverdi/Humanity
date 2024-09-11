package com.dogactanriverdi.humanity.presentation.happiness

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dogactanriverdi.humanity.R
import com.dogactanriverdi.humanity.common.viewBinding
import com.dogactanriverdi.humanity.databinding.FragmentHappinessBinding

class HappinessFragment : Fragment(R.layout.fragment_happiness) {

    private val binding by viewBinding(FragmentHappinessBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}