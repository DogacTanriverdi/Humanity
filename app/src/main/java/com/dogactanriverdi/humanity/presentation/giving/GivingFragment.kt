package com.dogactanriverdi.humanity.presentation.giving

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dogactanriverdi.humanity.R
import com.dogactanriverdi.humanity.common.viewBinding
import com.dogactanriverdi.humanity.databinding.FragmentGivingBinding

class GivingFragment : Fragment(R.layout.fragment_giving) {

    private val binding by viewBinding(FragmentGivingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}