package com.utku.moviedbplanet.ui.root

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.base.data.entities.Movie
import com.base.data.remote.Result
import com.base.data.repository.MovieDBPlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(private val repository: MovieDBPlanetRepository) :
    ViewModel() {

    private val _showProgress = MutableLiveData(false)
    val showProgress: LiveData<Boolean> by ::_showProgress

    private val _showError = MutableLiveData<String?>()
    val showError: LiveData<String?> by ::_showError

    private val _nowPlayingShowList = MutableLiveData<PagingData<Movie>>()
    val nowPlayingShowList: MutableLiveData<PagingData<Movie>> by ::_nowPlayingShowList

    private val _upComingMovieList = MutableLiveData<PagingData<Movie>>()
    val upComingMovieList: MutableLiveData<PagingData<Movie>> by ::_upComingMovieList

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie> by ::_movieDetail

    init {
        freshData()
    }

    private fun nowPlayingShowList() {
        viewModelScope.launch {
            repository.nowPlayingShowList().flow.cachedIn(this).collectLatest {
                _nowPlayingShowList.value = it
            }
        }
    }

    private fun upComingMovieList() {
        viewModelScope.launch {
            repository.upComingShowList().flow.cachedIn(this).collectLatest {
                _upComingMovieList.value = it
            }
        }
    }

    fun freshData() {
        nowPlayingShowList()
        upComingMovieList()
    }

    fun movieDetail(id: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.showDetail(id).collect {
                when (it) {
                    is Result.Success -> {
                        if (it.data != null) {
                            _movieDetail.value = it.data!!
                            onSuccess()
                        } else _showError.value = "null"
                    }
                    is Result.Error -> _showError.value = it.exception
                }
            }
        }
    }

    fun pagingStateController(state: LoadState) {
        if (state is LoadState.Error) {
            _showError.value = state.error.localizedMessage
        } else {
            _showProgress.value = state is LoadState.Loading
        }
    }

}