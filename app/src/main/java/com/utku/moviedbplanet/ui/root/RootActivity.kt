package com.utku.moviedbplanet.ui.root

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import com.base.ui.BaseActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.utku.moviedbplanet.R
import com.utku.moviedbplanet.databinding.ActivityRootBinding
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper

@AndroidEntryPoint
class RootActivity : BaseActivity<ActivityRootBinding>({ ActivityRootBinding.inflate(it) }) {

    override val viewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showError.observe(this) {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.warning)
                .setMessage(it)
                .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        if (newBase != null) super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
        else super.attachBaseContext(newBase)
    }
}