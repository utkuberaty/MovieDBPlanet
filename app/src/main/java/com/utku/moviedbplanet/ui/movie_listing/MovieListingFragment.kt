package com.utku.moviedbplanet.ui.movie_listing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

    private val movieNowPlayingPagingAdapter = MoviePagingAdapter(
        MovieType.NOW_PLAYING, ::navigateDetail
    )
    private val movieUpComingPagingAdapter = MoviePagingAdapter(
        MovieType.UP_COMING, ::navigateDetail
    )

    private var isNavigating = false

    override val viewModel: RootViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        PagerSnapHelper().attachToRecyclerView(viewBinding.nowPlayingRecyclerView)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        observeSwipeRefreshState()
        setRecyclerViews()
        loadingState()
        dataObservers()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun observeSwipeRefreshState() {
        viewModel.showProgress.observe(viewLifecycleOwner) {
            viewBinding.swipeRefreshLayout.isRefreshing = it
        }
        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.freshData()
        }
    }

    private fun setRecyclerViews() {
        with(viewBinding) {
            nowPlayingRecyclerView.adapter = movieNowPlayingPagingAdapter
            indicator.attachToRecyclerView(nowPlayingRecyclerView)
            upComingRecyclerview.adapter = movieUpComingPagingAdapter
            upComingRecyclerview.isNestedScrollingEnabled = true
        }
    }

    private fun dataObservers() {
        viewModel.nowPlayingShowList.observe(viewLifecycleOwner) {
            movieNowPlayingPagingAdapter.submitData(lifecycle, it)
        }
        viewModel.upComingMovieList.observe(viewLifecycleOwner) {
            movieUpComingPagingAdapter.submitData(lifecycle, it)
        }
    }

    private fun loadingState() {
        viewLifecycleOwner.lifecycleScope.launch {
            movieUpComingPagingAdapter.loadStateFlow.collectLatest {
                viewModel.pagingStateController(it.refresh)
            }
        }
    }

    private fun navigateDetail(id: String) {
        if (!isNavigating) {
            isNavigating = true
            viewModel.movieDetail(id) {
                findNavController().navigate(
                    MovieListingFragmentDirections.actionMovieListingFragmentToMovieDetailFragment()
                )
                isNavigating = false
            }
        }
    }
}