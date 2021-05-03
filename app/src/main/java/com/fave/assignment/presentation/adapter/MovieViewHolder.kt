package com.fave.assignment.presentation.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fave.assignment.R
import com.fave.assignment.data.model.movies.Movies
import com.fave.assignment.databinding.MovieItemBinding
import com.fave.assignment.utils.AppUtils
import com.fave.movie.utils.GlideRequests

class MovieViewHolder(private val binding: MovieItemBinding, private val glide: GlideRequests) :
    RecyclerView.ViewHolder(binding.root) {

    private var movies: Movies? = null


    fun bind(
        movies: Movies?,
        mClickController: MovieListAdapter.OnCellClickListener?,
        context: Context
    ) {
        this.movies = movies



        binding.apply {
            tvTitle.text = movies?.originalTitle
            tvPopularity.text = movies?.popularity.toString()

            root.setOnClickListener {
                    mClickController?.onCellClicked(movies!!, root)
            }

            if (movies != null) {
                if(!TextUtils.isEmpty(movies.posterPath)){
                    val strUrlImg =
                        context.getString(R.string.img_url_book) + "w220_and_h330_face${movies.posterPath}"

                        glide.load(strUrlImg)
                            .into(imgViewPoster)

                }
            }


        }
    }

    companion object {
        fun create(parent: ViewGroup, glide: GlideRequests): MovieViewHolder {
            val itemView =
                MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MovieViewHolder(itemView, glide)
        }
    }


}