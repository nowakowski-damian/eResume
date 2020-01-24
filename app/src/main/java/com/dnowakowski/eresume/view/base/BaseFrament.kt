package com.dnowakowski.eresume.view.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnowakowski.eresume.view.MainViewModel

/**
 * Created by Damian Nowakowski on 29 January 2020
 */
open class BaseFrament: Fragment() {

    protected val mainViewModel: MainViewModel by lazy {
        val viewModelProvider =
            ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
        viewModelProvider.get(MainViewModel::class.java)
    }
}