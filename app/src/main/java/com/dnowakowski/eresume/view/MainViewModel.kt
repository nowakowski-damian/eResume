package com.dnowakowski.eresume.view

import android.view.View
import com.dnowakowski.eresume.R
import com.dnowakowski.eresume.model.Resume
import com.dnowakowski.eresume.service.ResumeService
import com.dnowakowski.eresume.util.rx.RxViewModel
import com.dnowakowski.eresume.util.rx.StateMapper
import com.dnowakowski.eresume.util.rx.skipNull
import com.dnowakowski.eresume.view.MainAction.FetchData
import com.dnowakowski.eresume.view.MainAction.SelectTab
import io.reactivex.Observable

/**
 * Created by Damian Nowakowski on 24 January 2020
 */
class MainViewModel(
    private val resumeService: ResumeService = ResumeService
): RxViewModel<MainAction, MainState>() {

    val about by lazy {
        resumeObservable.skipNull { it.about }.asLiveData()
    }

    val highlights by lazy {
        resumeObservable.skipNull { it.highlights }.asLiveData()
    }

    val experience by lazy {
        resumeObservable.skipNull { it.experience }.asLiveData()
    }

    val education by lazy {
        resumeObservable.skipNull { it.education }.asLiveData()
    }

    val currentTab by lazy {
        currentTabObservable.asLiveData()
    }

    val loadingIndicatorVisibility by lazy {
        loadingObservable
            .map { if(it) View.VISIBLE else View.GONE }
            .asLiveData()
    }

    val errorMessage by lazy {
        errorObservable
            .map { it.localizedMessage }
            .asLiveData()
    }

    override fun createInitialState() = MainState(R.id.action_about)

    override fun createObservable(action: MainAction): Observable<StateMapper<MainState>>? = when(action) {
        is SelectTab -> Observable.just(action)
            .map { it.id }
            .map { number -> StateMapper<MainState> { it.copy(selectedTab = number) } }
        FetchData -> fetchResume()
            .map { resume -> StateMapper<MainState> { it.copy(resume = resume) } }
    }

    override fun showLoading(action: MainAction): Boolean = when(action) {
        is FetchData -> true
        else -> false
    }

    private val currentTabObservable: Observable<Int>
    get() = stateObservable
        .map { it.selectedTab }
        .distinctUntilChanged()

    private val resumeObservable: Observable<Resume>
    get() = stateObservable
        .skipNull { it.resume }
        .distinctUntilChanged()

    private fun fetchResume() = resumeService.api().getResume().toObservable()
}