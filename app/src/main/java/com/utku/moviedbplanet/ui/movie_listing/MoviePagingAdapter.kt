package com.utku.moviedbplanet.ui.movie_listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.base.data.entities.Movie
import com.base.data.entities.MovieType
import com.base.data.util.IMAGE_BASE_URL
import com.utku.moviedbplanet.databinding.ItemNowPlayingMovieBinding
import com.utku.moviedbplanet.databinding.ItemUpComingMovieBinding

class MoviePagingAdapter(
    private val movieType: MovieType
) : PagingDataAdapter<Movie, RecyclerView.ViewHolder>(MovieComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (movieType) {
            MovieType.UP_COMING -> UpComingViewHolder(
                ItemUpComingMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            MovieType.NOW_PLAYING -> NowPlayingViewHolder(
                ItemNowPlayingMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UpComingViewHolder -> {
                holder.bind(getItem(position))
            }
            is NowPlayingViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    class UpComingViewHolder(
        private val viewBinding: ItemUpComingMovieBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(movie: Movie?) {
            if (movie != null) {
                with(viewBinding) {
                    with(movie) {
                        movieImageView.load("$IMAGE_BASE_URL/w500$posterPath")
                        movieTitleTextView.text = "$title(${releaseDate.split("-").first()})"
                        movieDescriptionTextView.text = overview
                        movieReleaseDateTextView.text = releaseDate.replace("-",".")
                    }
                }
            }
        }
    }

    class NowPlayingViewHolder(
        private val viewBinding: ItemNowPlayingMovieBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(movie: Movie?) {
            if (movie != null) {
                with(viewBinding) {
                    with(movie) {
                        movieImageView.load("$IMAGE_BASE_URL/w500$backdropPath")
                        movieTitleTextView.text = "$title(${releaseDate.split("-").first()})"
                        movieDescriptionTextView.text = overview
                    }
                }
            }
        }
    }

}


object MovieComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
