package com.aeharake.choters.utils

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class CodeUtils {
    companion object {
        fun <T> emptySubscriber(): Observer<T> {
            return object : Observer<T> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: T) {
                }

                override fun onError(e: Throwable) {
                }
            }
        }
    }
}