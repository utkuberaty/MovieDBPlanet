package com.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loadingObserver = MutableLiveData(false)
    val errorObserver = MutableLiveData<String>()
}