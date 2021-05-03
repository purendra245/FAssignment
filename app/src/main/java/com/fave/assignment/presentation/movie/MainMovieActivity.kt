package com.fave.assignment.presentation.movie

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fave.assignment.data.constants.Constants
import com.fave.assignment.data.model.movies.Movies
import com.fave.assignment.presentation.adapter.MovieListAdapter
import com.fave.assignment.presentation.adapter.MovieLoadStateAdapter
import com.fave.assignment.presentation.detail.MovieDetailActivity
import com.fave.assignment.utils.AppUtils
import com.fave.movie.utils.GlideApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.fave.assignment.R


@ExperimentalPagingApi
@AndroidEntryPoint
class MainMovieActivity : AppCompatActivity(),MovieListAdapter.OnCellClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    @ExperimentalCoroutinesApi
    private val movieViewModel: MainViewModel by viewModels()
    private lateinit var movieAdapter: MovieListAdapter
    lateinit var loaderStateAdapter: MovieLoadStateAdapter
    private var SORT_BY = Constants.SortBy.RELEASE_DATE_ASCENDING


    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecylerView()
        setUpRecycler()

        fetchMovieImages(SORT_BY)
    }


    @ExperimentalCoroutinesApi
    private fun fetchMovieImages(sortBy: Constants.SortBy) {
        lifecycleScope.launch {
            movieViewModel.fetchMovieData(sortBy).distinctUntilChanged().collectLatest {
                movieAdapter.submitData(it)
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }



    private fun initRecylerView() {
        swipeRefreshLayout.setOnRefreshListener(this);
        val glide = GlideApp.with(this)
        movieAdapter = MovieListAdapter(glide,this)
        movieAdapter.setOnClickListener(this)
        loaderStateAdapter = MovieLoadStateAdapter (movieAdapter)
    }

    private fun setUpRecycler() {
        rvMovie.apply {
            val mNoOfColumns = AppUtils.calculateNoOfColumns(context,180f)
            layoutManager = GridLayoutManager(context,mNoOfColumns)
            setHasFixedSize(true)
            adapter = movieAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter( movieAdapter)
            )
        }

    }

    override fun onCellClicked(image: Movies, view: View) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(Constants.MOVIE_ID, image.id)
        intent.putExtra(Constants.MOVIE_TITLE, image.title)
        startActivity(intent)

    }

    @ExperimentalCoroutinesApi
    override fun onRefresh() {
        refreshMovieData()
    }


    private fun showLoading(isRefresh: Boolean) {
        if (isRefresh) {
            if (!swipeRefreshLayout.isRefreshing) {
                swipeRefreshLayout.isRefreshing = true
            }
        } else {
            progressBarMovie.visibility = View.VISIBLE

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }


    @ExperimentalCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.item_alpha -> {
                SORT_BY = Constants.SortBy.ORIGINAL_TITLE_ASCENDING
                refreshMovieData()
                true
            }
            R.id.item_release_date -> {
                SORT_BY = Constants.SortBy.RELEASE_DATE_ASCENDING
                refreshMovieData()
                true
            }
            R.id.item_rating -> {
                SORT_BY = Constants.SortBy.VOTE_AVERAGE_ASCENDING
                refreshMovieData()
                true
            }
            else -> super.onOptionsItemSelected(item)


        }
    }


    fun refreshMovieData(){
        showLoading(true)
        fetchMovieImages(SORT_BY)
    }

}
