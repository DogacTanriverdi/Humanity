package com.dogactanriverdi.humanity.presentation.kindness

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dogactanriverdi.humanity.R
import com.dogactanriverdi.humanity.common.viewBinding
import com.dogactanriverdi.humanity.databinding.FragmentKindnessBinding

class KindnessFragment : Fragment(R.layout.fragment_kindness) {

    private val binding by viewBinding(FragmentKindnessBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireView())
            .asGif()
            .load(R.drawable.kindness)
            .into(binding.ivKindness)
    }
}