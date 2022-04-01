package com.utku.moviedbplanet.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.base.ui.BaseFragment
import com.utku.moviedbplanet.databinding.FragmentMovieDetailBinding
import com.utku.moviedbplanet.ui.root.RootViewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>({
    FragmentMovieDetailBinding.inflate(it)
}) {

    override val viewModel: RootViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}