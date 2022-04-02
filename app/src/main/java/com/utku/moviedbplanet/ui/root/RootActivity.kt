package com.utku.moviedbplanet.ui.root

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.base.ui.BaseActivity
import com.utku.moviedbplanet.databinding.ActivityRootBinding
import com.utku.moviedbplanet.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper

@AndroidEntryPoint
class RootActivity : BaseActivity<ActivityRootBinding>({ ActivityRootBinding.inflate(it) }) {

    override val viewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showError.observe(this) {
            Log.i(TAG, "showError $it")
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        if (newBase != null) super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
        else super.attachBaseContext(newBase)
    }
}