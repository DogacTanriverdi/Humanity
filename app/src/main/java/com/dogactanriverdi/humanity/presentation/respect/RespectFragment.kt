package com.dogactanriverdi.humanity.presentation.respect

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dogactanriverdi.humanity.R
import com.dogactanriverdi.humanity.common.viewBinding
import com.dogactanriverdi.humanity.databinding.FragmentRespectBinding

class RespectFragment : Fragment(R.layout.fragment_respect) {

    private val binding by viewBinding(FragmentRespectBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            Glide.with(requireContext())
                .asGif()
                .load(R.drawable.respect)
                .into(ivRespect)

            btnF.setOnClickListener {
                tvPressFToPayRespects.visibility = View.GONE
                btnF.visibility = View.GONE
                ivRespect.visibility = View.VISIBLE
            }
        }
    }
}