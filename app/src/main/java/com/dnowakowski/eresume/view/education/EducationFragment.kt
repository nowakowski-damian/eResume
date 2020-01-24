package com.dnowakowski.eresume.view.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnowakowski.eresume.databinding.FragmentEducationBinding
import com.dnowakowski.eresume.view.base.BaseFrament

/**
 * Created by Damian Nowakowski on 28 January 2020
 */
class EducationFragment: BaseFrament() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentEducationBinding.inflate(inflater).apply {
            education = mainViewModel.education
            lifecycleOwner = this@EducationFragment
            educationList.adapter = EducationAdapter()
        }.root
}