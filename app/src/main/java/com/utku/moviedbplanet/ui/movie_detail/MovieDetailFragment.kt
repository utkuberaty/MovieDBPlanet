package com.utku.moviedbplanet.ui.movie_detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import coil.load
import com.base.data.util.BIG_IMAGE
import com.base.data.util.MAX_VOTE
import com.base.ui.BaseFragment
import com.utku.moviedbplanet.databinding.FragmentMovieDetailBinding
import com.utku.moviedbplanet.ui.root.RootViewModel


class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>({
    FragmentMovieDetailBinding.inflate(it)
}) {

    override val viewModel: RootViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setMovieData()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setMovieData() {
        with(viewBinding) {
            viewModel.movieDetail.value?.let { movie ->
                movieImageView.load("$BIG_IMAGE${movie.backdropPath}")
                voteTextView.setText(
                    voteText(movie.voteAverage.toString()),
                    TextView.BufferType.SPANNABLE
                )
                releaseDateTextView.text = movie.releaseDate
                movieTitleTextView.text = movie.titleWithYear
                movieDescriptionTextView.text = movie.overview
                imdbLogoImageView.setOnClickListener { intentIMDB(movie.imdbId) }
            }
        }
    }

    private fun intentIMDB(id: String) {
        val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse("imdb:///title/$id"))
        try {
            startActivity(sendIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context, ex.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun voteText(vote: String): SpannableString {
        val text = "$vote/$MAX_VOTE"
        return SpannableString(text).apply {
            setSpan(
                ForegroundColorSpan(Color.GRAY),
                vote.length,
                "/$MAX_VOTE".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}