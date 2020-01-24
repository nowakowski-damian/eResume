package com.dnowakowski.eresume.view.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnowakowski.eresume.databinding.FragmentAboutBinding
import com.dnowakowski.eresume.view.base.BaseFrament

/**
 * Created by Damian Nowakowski on 24 January 2020
 */
class AboutFragment: BaseFrament() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentAboutBinding.inflate(inflater).apply {
            about = mainViewModel.about
            lifecycleOwner = this@AboutFragment
        }.root
}