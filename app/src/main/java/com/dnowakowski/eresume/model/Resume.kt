package com.dnowakowski.eresume.model

/**
 * Created by Damian Nowakowski on 24 January 2020
 */
data class Resume(
    val about: About,
    val highlights: List<String>,
    val experience: List<Experience>,
    val education: List<Education>
)