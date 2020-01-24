package com.dnowakowski.eresume.view

/**
 * Created by Damian Nowakowski on 24 January 2020
 */

sealed class MainAction {
    class SelectTab(val id: Int): MainAction()
    object FetchData: MainAction()
}