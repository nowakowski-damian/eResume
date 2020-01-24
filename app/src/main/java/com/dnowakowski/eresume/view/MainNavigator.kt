package com.dnowakowski.eresume.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnowakowski.eresume.R
import com.dnowakowski.eresume.util.rx.observe
import com.dnowakowski.eresume.view.about.AboutFragment
import com.dnowakowski.eresume.view.education.EducationFragment
import com.dnowakowski.eresume.view.experience.ExperienceFragment
import com.dnowakowski.eresume.view.highlights.HighlightsFragment

/**
 * Created by Damian Nowakowski on 29 January 2020
 */
class MainNavigator(private val activity: MainActivity) {

    private val mainViewModel: MainViewModel by lazy {
        val viewModelProvider = ViewModelProvider(activity, ViewModelProvider.NewInstanceFactory())
        viewModelProvider.get(MainViewModel::class.java)
    }

    init {
        mainViewModel.currentTab.observe(activity) {
            val fragment = when(it) {
                R.id.action_about -> AboutFragment()
                R.id.action_highlights -> HighlightsFragment()
                R.id.action_experience -> ExperienceFragment()
                R.id.action_education -> EducationFragment()
                else -> Fragment()
            }
            replaceFragment(fragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .disallowAddToBackStack()
            .commit()
    }
}