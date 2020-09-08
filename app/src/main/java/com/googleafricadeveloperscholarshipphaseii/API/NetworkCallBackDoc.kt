package com.testkotlin.API

import io.reactivex.disposables.Disposable

interface NetworkCallBackDoc<T> {
    fun onSuccess(response: T?);
    fun onFailure(error: String?);
    fun onDispose(disposable: Disposable?);
}