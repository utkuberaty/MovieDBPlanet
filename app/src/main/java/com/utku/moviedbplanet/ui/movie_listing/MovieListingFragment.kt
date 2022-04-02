package com.utku.moviedbplanet.ui.movie_listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.base.data.entities.MovieType
import com.base.ui.BaseFragment
import com.utku.moviedbplanet.databinding.FragmentMovieListingBinding
import com.utku.moviedbplanet.ui.root.RootViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListingFragment : BaseFragment<FragmentMovieListingBinding>({
    FragmentMovieListingBinding.inflate(it)
}) {

    private val movieNowPlayingPagingAdapter = MoviePagingAdapter(MovieType.NOW_PLAYING)
    private val movieUpComingPagingAdapter = MoviePagingAdapter(MovieType.UP_COMING)

    override val viewModel: RootViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        observeSwipeRefreshState()
        setRecyclerViews()
        loadingState()
        freshData()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun observeSwipeRefreshState() {
        viewModel.showProgress.observe(viewLifecycleOwner) {
           viewBinding.swipeRefreshLayout.isRefreshing = it
        }
        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            freshData()
        }
    }

    private fun setRecyclerViews() {
        viewBinding.nowPlayingRecyclerView.adapter = movieNowPlayingPagingAdapter
        viewBinding.upComingRecyclerview.adapter = movieUpComingPagingAdapter
        PagerSnapHelper().attachToRecyclerView(viewBinding.nowPlayingRecyclerView)
    }

    private fun freshData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.nowPlayingShowList.collectLatest {
                movieNowPlayingPagingAdapter.submitData(lifecycle, it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.upComingMovieList.collectLatest {
                movieUpComingPagingAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun loadingState() {
        viewLifecycleOwner.lifecycleScope.launch {
            movieUpComingPagingAdapter.loadStateFlow.collectLatest {
                viewModel.pagingStateController(it.refresh)
            }
        }
    }
}