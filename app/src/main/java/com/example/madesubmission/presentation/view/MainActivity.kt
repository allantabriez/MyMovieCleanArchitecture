package com.example.madesubmission.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madesubmission.R
import com.example.madesubmission.core.domain.model.Movie
import com.example.madesubmission.presentation.viewmodel.MainViewModel
import com.example.madesubmission.core.ui.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val movieAdapter: RecyclerViewAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTopBar()

        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.movies.observe(this, Observer { movies ->
            if (movies != null) loadData(movies)
            else Toast.makeText(this, getString(R.string.cannot_load_content), Toast.LENGTH_LONG)
                .show()
        })
    }

    private fun loadData(data: List<Movie>) {
        mainBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        movieAdapter.setData(data)
    }

    private fun setTopBar(){
        topAppBar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.favorite) {
                val favoriteIntent = Intent(
                    this,
                    Class.forName("com.example.madesubmission.favorite.presentation.FavoriteActivity")
                )
                startActivity(favoriteIntent)
                true
            } else if (item.itemId == R.id.changeLanguage) {
                val languageIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(languageIntent)
                true
            } else false
        }
    }
}