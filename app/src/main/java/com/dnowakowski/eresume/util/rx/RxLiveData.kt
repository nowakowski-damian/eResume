package com.dnowakowski.eresume.util.rx

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.TestObserver

/**
 * Created by Damian Nowakowski on 24 January 2020
 */
class RxLiveData<T>(val observable: Observable<T>) : MutableLiveData<T>() {

    private var disposable: Disposable? = null

    override fun removeObserver(observer: Observer<in T>) {
        super.removeObserver(observer)
        // dispose the subscription if there is no observers (including active and in-active)
        // there is no race condition here, because it is running in the MainThread
        if ( !this.hasObservers() ) {
            disposable?.dispose()
            disposable = null
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val hasObservables = this.hasObservers()
        super.observe(owner, observer)

        if (!hasObservables) {
            // there is no race condition here, because it is running in the MainThread
            subscribeToObservable()
        }
    }

    override fun observeForever(observer: Observer<in T>) {
        val hasObservables = this.hasObservers()
        super.observeForever(observer)
        if (!hasObservables) {
            // there is no race condition here, because it is running in the MainThread
            subscribeToObservable()
        }
    }

    private fun subscribeToObservable() {
        disposable = observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { value -> setValue(value) }
    }

    fun test(): TestObserver<T> = observable.test()
}