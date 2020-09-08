package com.testkotlin.API

import io.reactivex.disposables.Disposable

interface NetworkCallBack<T> {
    fun onSuccess(response: List<T>?);
    fun onFailure(error: String?);
    fun onDispose(disposable: Disposable?);
}