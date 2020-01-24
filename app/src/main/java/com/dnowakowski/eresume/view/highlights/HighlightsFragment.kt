package com.dnowakowski.eresume.view.highlights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dnowakowski.eresume.databinding.FragmentHighlightsBinding
import com.dnowakowski.eresume.view.base.BaseFrament

/**
 * Created by Damian Nowakowski on 27 January 2020
 */
class HighlightsFragment: BaseFrament() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        FragmentHighlightsBinding.inflate(inflater).apply {
            highlights = mainViewModel.highlights
            lifecycleOwner = this@HighlightsFragment
            highlightsList.adapter = HighlightsAdapter()
        }.root
}