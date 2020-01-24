package com.dnowakowski.eresume.util.rx

import androidx.lifecycle.LifecycleOwner
import io.reactivex.Observable
import java.util.*

fun <T> RxLiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, androidx.lifecycle.Observer {
        it?.let(observer)
    })
}

fun <T> Observable<T>.asLiveData(viewModel: RxViewModel<*,*>): RxLiveData<T> = viewModel.toLiveData(this)


fun <T, R> Observable<T>.skipNull(mapper: (t: T) -> R?): Observable<R> {
    return this.map { Optional.ofNullable(mapper.invoke(it)) }
            .flatMap { if (it.isPresent) Observable.just(it.get()) else Observable.empty() }
}
