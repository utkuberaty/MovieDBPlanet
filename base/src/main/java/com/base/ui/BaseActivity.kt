package com.base.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.base.ui.util.TAG

/**
 * Abstract base activity for all activities, all activity extends [BaseActivity]
 * abstract viewModel forces all activities to use viewModel
 * */

abstract class BaseActivity<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    AppCompatActivity() {

    abstract val viewModel: ViewModel

    protected val viewBinding: VB by lazy { bindingFactory(layoutInflater) }

    /**
     * Activity lifeCycle methods, It's calls when activity create
     * Sets given viewBiding [VB] from constructor to activity setContentView automatically
     * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setContentView(viewBinding.root)
    }
}