package com.utku.moviedbplanet.ui.root

import androidx.activity.viewModels
import com.base.ui.BaseActivity
import com.utku.moviedbplanet.databinding.ActivityRootBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : BaseActivity<ActivityRootBinding>({ ActivityRootBinding.inflate(it) }) {

    override val viewModel: RootViewModel by viewModels()

}