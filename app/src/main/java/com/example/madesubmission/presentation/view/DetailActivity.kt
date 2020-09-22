package com.example.madesubmission.presentation.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.madesubmission.R
import com.example.madesubmission.core.domain.model.Details
import com.example.madesubmission.core.domain.model.Movie
import com.example.madesubmission.core.utils.GlideMovie
import com.example.madesubmission.presentation.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(), DialogFragment.OnDialogListener {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val viewModel: DetailViewModel by viewModel()
    private var isFavorite = false
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setUpToolBar()
        movie = intent.getParcelableExtra<Movie>(EXTRA_DATA) as Movie
        loadImage(movie.imagePath)
        viewModel.getDetails(movie.id).observe(this, Observer { details ->
            if (details != null) {
                loadDetails(details, movie)
                showUI()
            } else {
                loadDetails(null, movie)
            }
        })
        viewModel.checkFavorite(movie.id).observe(this, Observer { isFavorite ->
            if (isFavorite) setFavorite(true)
            else if (!isFavorite) setFavorite(false)
        })
        favoriteFab.setOnClickListener {
            try {
                createFavorite(movie)
            } catch (e: Exception){
                Toast.makeText(this, getString(R.string.module_not_found), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun loadDetails(details: Details?, movie: Movie) {
        if (details != null){
            detailTitle.text = movie.title
            detailVoteAverage.text = movie.voteAverage.toString()
            detailRelease.text = movie.releaseDate
            detailDescription.text = movie.overView
            detailPopularity.text = movie.popularity.toString()
            detailTagLine.text = details.tagLine
            detailRuntime.text = details.runTime.toString()
            detailGenre.text = details.genres.toString()
        } else {
            detailBar.visibility = View.GONE
            Toast.makeText(this, getString(R.string.cannot_load_content), Toast.LENGTH_LONG).show()
        }
    }

    private fun setFavorite(boolean: Boolean){
        if (boolean){
            isFavorite = true
            favoriteFab.setImageResource(R.drawable.logo_favorite)
        }
        else {
            isFavorite = false
            favoriteFab.setImageResource(R.drawable.logo_not_favorite)
        }
    }

    private fun createFavorite(movie: Movie){
        if (!isFavorite){
            viewModel.insertFavoriteMovie(movie)
            setFavorite(true)
        } else {
            val bundle = Bundle()
            bundle.putString(DialogFragment.EXTRA_DATA, movie.title)
            val dialog = DialogFragment()
            dialog.arguments = bundle
            dialog.show(supportFragmentManager, DialogFragment::class.java.simpleName)
        }
    }

    private fun setUpToolBar(){
        setSupportActionBar(toolBar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.logo_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolBar.title = getString(R.string.details)
        collapsingToolBar.setExpandedTitleColor(Color.WHITE)
        collapsingToolBar.setCollapsedTitleTextColor(Color.WHITE)
    }

    private fun loadImage(imagePath: String){
        Glide.with(this)
            .load(GlideMovie.createImagePath(imagePath))
            .centerInside()
            .into(filmImage)
    }

    private fun showUI(){
        detailBar.visibility = View.GONE
        detailView.visibility = View.VISIBLE
        filmImage.visibility = View.VISIBLE
    }

    override fun onRetrieved(text: String) {
        if (text == "Delete") {
            viewModel.deleteFavoriteMovie(movie)
            setFavorite(false)
        }
    }
}