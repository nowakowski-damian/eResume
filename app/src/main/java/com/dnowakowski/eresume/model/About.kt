package com.dnowakowski.eresume.model

/**
 * Created by Damian Nowakowski on 24 January 2020
 */
data class About(
    val name: String,
    val photo: String,
    val location: String,
    val contactDetails: ContactDetails,
    val description: String
)