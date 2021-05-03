package com.fave.assignment.presentation.movie

import androidx.recyclerview.widget.DiffUtil
import com.fave.assignment.data.model.movies.Movies


class DiffUtilMovie: DiffUtil.ItemCallback<Movies>() {

    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return newItem.id == oldItem.id
    }


}