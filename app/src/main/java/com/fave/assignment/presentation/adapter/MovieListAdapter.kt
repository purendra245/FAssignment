package com.fave.assignment.presentation.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.fave.assignment.data.model.movies.Movies
import com.fave.assignment.presentation.movie.DiffUtilMovie
import com.fave.movie.utils.GlideRequests

class MovieListAdapter(private val glide: GlideRequests, val context: Context)
    : PagingDataAdapter<Movies, MovieViewHolder>(DiffUtilMovie()) {

    var mClickController: OnCellClickListener? = null

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position),mClickController,context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent, glide)
    }



    interface OnCellClickListener {
        fun onCellClicked(image: Movies, view: View)
    }
    fun setOnClickListener(listener: OnCellClickListener) {
        mClickController = listener
    }
}