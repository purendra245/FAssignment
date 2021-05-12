package com.fave.assignment.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import androidx.paging.ExperimentalPagingApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.fave.assignment.R
import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.model.ResultData
import com.fave.assignment.data.model.detail.Genre
import com.fave.assignment.data.model.detail.Movie
import com.fave.assignment.data.model.detail.SpokenLanguage
import com.fave.assignment.databinding.ActivityMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPagingApi
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    private val movieViewModel: MovieDetailViewModel by viewModels()
    private var movieId: Int = 0
    private lateinit var binding: ActivityMovieDetailBinding


    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            movieId = extras.getInt(Constants.MOVIE_ID)
            val movieTitle =
                extras.getString(Constants.MOVIE_TITLE)
            title = movieTitle
        }
        initViewModel()
        setBookMovie()
    }

    private fun setBookMovie() {
       btnBook.setOnClickListener{
           val url = getString(R.string.web_url_book) + movieId
           try {
               val i = Intent(Intent.ACTION_VIEW)
               i.data = Uri.parse(url)
               startActivity(i)
           }catch (e:Exception){

           }

       }

    }


    @ExperimentalCoroutinesApi
    private fun initViewModel() {

        val listMovie = movieViewModel.getMoviesDetail(movieId)
        listMovie.observe(this, { resultData ->
            when (resultData) {
                is ResultData.Loading -> {
                    binding.progressBar.visibility  = View.VISIBLE
                }
                is ResultData.Success -> {
                    val movie = resultData.data
                    showMovieDetail(movie)
                    binding.progressBar.visibility  = View.GONE
                }
                is ResultData.Failed -> {
                    binding.progressBar.visibility  = View.GONE
                }
                is ResultData.Error -> {
                    binding.progressBar.visibility  = View.GONE
                }
            }
        })
    }


    private fun showMovieDetail(movie: Movie) {

        if(!TextUtils.isEmpty(movie.posterPath) || !TextUtils.isEmpty(movie.backdropPath)){
            val fullImageUrl: String = getFullImageUrl(movie)
            if (fullImageUrl.isNotEmpty()) {
                Glide.with(this)
                    .load(fullImageUrl)
                    .apply(RequestOptions.centerCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgVPoster)
            }
        }

        tvoverview.text = movie.overview?.let { getOverview(it) }
        tvGenres.text = getGenres(movie)
        tvDuration.text = getDuration(movie)
        tvLanguage.text = getLanguages(movie)



    }

    private fun getDuration(movie: Movie): String {
        val runtime = movie.runtime
        return if (runtime <= 0) "-" else resources.getQuantityString(
            R.plurals.duration,
            runtime,
            runtime
        )
    }

    private fun getOverview(overview: String): String {
        return if (TextUtils.isEmpty(overview)) "-" else overview
    }

    private fun getFullImageUrl(movie: Movie): String {

        val imagePath: String? = if (movie.posterPath != null && movie.posterPath!!.isNotEmpty()) {
            movie.posterPath
        } else {
            movie.backdropPath
        }
        return getString(R.string.img_url_book)+"w500${imagePath}"
    }

    private fun getGenres(movie: Movie): String {
        var genres = ""
        for (element in movie.genres!!) {
            val genre: Genre = element
            genres += genre.name.toString() + ", "
        }
        genres = removeTrailingComma(genres)
        return if (genres.isEmpty()) "-" else genres
    }

    private fun getLanguages(movie: Movie): String {
        var languages = ""
        for (i in movie.spokenLanguages!!.indices) {
            val language: SpokenLanguage = movie.spokenLanguages!![i]
            languages += language.name.toString() + ", "
        }
        languages = removeTrailingComma(languages)
        return if (languages.isEmpty()) "-" else languages
    }

    private fun removeTrailingComma(tVal: String): String {
        var text = tVal
        text = text.trim { it <= ' ' }
        if (text.endsWith(",")) {
            text = text.substring(0, text.length - 1)
        }
        return text
    }

}
