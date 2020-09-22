package com.example.madesubmission.favorite.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madesubmission.favorite.R
import com.example.madesubmission.core.domain.model.Movie
import com.example.madesubmission.core.ui.RecyclerViewAdapter
import com.example.madesubmission.favorite.di.favoriteModule
import com.example.madesubmission.presentation.view.DetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_favorite.recyclerView
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {


    private val viewModel: FavoriteViewModel by viewModel()
    private val favoriteAdapter : RecyclerViewAdapter by inject<RecyclerViewAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = getString(R.string.favorite)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.logo_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(recyclerView){
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = favoriteAdapter
        }
        favoriteAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.favorites.observe(this, Observer { favorites ->
            if (favorites.isNotEmpty()) loadData(favorites)
            else if (favorites.isEmpty()) {
                favoriteBar.visibility = View.GONE
                animationView.visibility = View.VISIBLE
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun loadData(data: List<Movie>) {
        favoriteBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        favoriteAdapter.setData(data)
    }
}