package com.dnowakowski.eresume.util.binding

/**
 * Created by Damian Nowakowski on 27 January 2020
 */
interface BindableAdapter<T> {
    fun setData(data: List<T>)
}