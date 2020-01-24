package com.dnowakowski.eresume.view

import com.dnowakowski.eresume.model.Resume

/**
 * Created by Damian Nowakowski on 24 January 2020
 */

data class MainState(
    val selectedTab: Int,
    val resume: Resume? = null
)