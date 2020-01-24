package com.dnowakowski.eresume.view

import com.dnowakowski.eresume.R
import com.dnowakowski.eresume.RxSchedulerRule
import com.dnowakowski.eresume.model.*
import com.dnowakowski.eresume.service.ResumeApi
import com.dnowakowski.eresume.service.ResumeService
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Damian Nowakowski on 29 January 2020
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule @JvmField
    val testSchedulerRule = RxSchedulerRule()

    @Mock
    lateinit var resumeApi: ResumeApi

    @Mock
    lateinit var resumeService: ResumeService

    private lateinit var mainViewModel: MainViewModel


    @Before
    fun setUp() {
        `when`(resumeApi.getResume()).thenReturn(Single.just(mockResume()))
        `when`(resumeService.api()).thenReturn(resumeApi)
        mainViewModel = MainViewModel(resumeService)
    }


    @Test
    fun getAbout() {

        val aboutTest = mainViewModel.about.test()

        aboutTest.assertLiveDataNoValue()

        mainViewModel.executeAction(MainAction.FetchData)

        aboutTest.assertLiveDataValue(mockAbout())
    }

    @Test
    fun getHighlights() {

        val aboutTest = mainViewModel.highlights.test()

        aboutTest.assertLiveDataNoValue()

        mainViewModel.executeAction(MainAction.FetchData)

        aboutTest.assertLiveDataValue(mockHighlights())
    }

    @Test
    fun getExperience() {

        val aboutTest = mainViewModel.experience.test()

        aboutTest.assertLiveDataNoValue()

        mainViewModel.executeAction(MainAction.FetchData)

        aboutTest.assertLiveDataValue(mockExperience())
    }

    @Test
    fun getEducation() {

        val aboutTest = mainViewModel.education.test()

        aboutTest.assertLiveDataNoValue()

        mainViewModel.executeAction(MainAction.FetchData)

        aboutTest.assertLiveDataValue(mockEducation())
    }

    @Test
    fun getCurrentTab() {

        val aboutTest = mainViewModel.currentTab.test()

        aboutTest.assertLiveDataValue(R.id.action_about)

        mainViewModel.executeAction(MainAction.SelectTab(R.id.action_experience))

        aboutTest.assertLiveDataValue(R.id.action_about,R.id.action_experience)
    }

    private fun mockContactDetails() = ContactDetails("t", "t", "t")
    private fun mockAbout() = About("t", "t", "t", mockContactDetails(), "t")
    private fun mockCompany() = Company("t", "t", "t", "t")
    private fun mockResponsibilities() = listOf("t")
    private fun mockExperience() = listOf( Experience(mockCompany(), "t", "t", "t", mockResponsibilities()) )
    private fun mockEducation() = listOf( Education("t", "t", "t", "t", "t", "t") )
    private fun mockHighlights() = listOf("t")
    private fun mockResume() = Resume(mockAbout(), mockHighlights(), mockExperience(), mockEducation())

    private fun <T> TestObserver<T>.assertLiveDataNoValue() =
        assertNoValues()
        .assertNoErrors()


    private fun <T> TestObserver<T>.assertLiveDataValue(vararg data: T) =
        awaitCount(data.count())
        .assertNoErrors()
        .assertNotComplete()
        .assertNotTerminated()
        .assertValues(*data)
}