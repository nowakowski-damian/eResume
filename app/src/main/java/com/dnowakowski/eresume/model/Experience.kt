package com.dnowakowski.eresume.model

/**
 * Created by Damian Nowakowski on 24 January 2020
 */
data class Experience(
    val company: Company,
    val role: String,
    val startDate: String,
    val endDate: String,
    val responsibilities: List<String>
) {
    companion object {
        const val DATE_FORMAT = "dd-MM-yyyy"
    }
}