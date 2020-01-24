package com.dnowakowski.eresume.view.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnowakowski.eresume.databinding.FragmentExperienceBinding
import com.dnowakowski.eresume.view.base.BaseFrament

/**
 * Created by Damian Nowakowski on 27 January 2020
 */
class ExperienceFragment: BaseFrament() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentExperienceBinding.inflate(inflater).apply {
            experience = mainViewModel.experience
            lifecycleOwner = this@ExperienceFragment
            experienceList.adapter = ExperienceAdapter()
        }.root
}