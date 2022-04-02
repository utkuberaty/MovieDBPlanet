package com.base.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.base.ui.util.TAG

/**
 * Abstract base fragment for all fragments, all fragment extends [BaseFragment]
 * abstract viewModel forces all fragments to use viewModel
 * */

abstract class BaseFragment<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB): Fragment() {

    abstract val viewModel: ViewModel

    protected val viewBinding: VB by lazy { bindingFactory(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG,"onCreateView")
        return viewBinding.root
    }

}