package com.base.data.util

import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import com.base.data.remote.Result

suspend fun <T> LiveDataScope<T?>.returnResult(
    result: Result<T?>,
    errorObserver: MutableLiveData<String>
) {
    if (result is Result.Success) emit(result.data)
    else if (result is Result.Error) errorObserver.value = result.exception
}