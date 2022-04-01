package com.utku.moviedbplanet.ui.root

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.data.remote.Result
import com.base.data.repository.MovieDBPlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(private val repository: MovieDBPlanetRepository) :
    ViewModel() {

    private val _showProgress = MutableLiveData(false)
    val showProgress: LiveData<Boolean> by ::_showProgress

    private val _showError = MutableLiveData<String?>()
    val showError: LiveData<String?> by ::_showError

    private val _nowPlayingShowList = MutableLiveData<String?>()
    val nowPlayingShowList: LiveData<String?> by ::_nowPlayingShowList

    fun upComingMovieList() {
        viewModelScope.launch {
            repository.upComingShowList().onStart {
                _showProgress.value = true
            }.onCompletion { _showProgress.value = false }.collect {
                when (it) {
                    is Result.Success -> {}
                    is Result.Error -> _showError.value = it.exception
                }
            }
        }
    }

    fun nowPlayingShowList() {
        viewModelScope.launch {
            repository.nowPlayingShowList().onStart {
                _showProgress.value = true
            }.onCompletion { _showProgress.value = false }.collect {
                when (it) {
                    is Result.Success -> {}
                    is Result.Error -> _showError.value = it.exception
                }
            }
        }
    }

    fun showDetail(id: String) {
        viewModelScope.launch {
            repository.showDetail(id).onStart {
                _showProgress.value = true
            }.onCompletion { _showProgress.value = false }.collect {
                when (it) {
                    is Result.Success -> {}
                    is Result.Error -> _showError.value = it.exception
                }
            }
        }
    }

}