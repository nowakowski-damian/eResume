package com.dnowakowski.eresume.service

import com.dnowakowski.eresume.model.Resume
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Damian Nowakowski on 24 January 2020
 */
interface ResumeApi {
    @GET("resume.json")
    fun getResume(): Single<Resume>
}