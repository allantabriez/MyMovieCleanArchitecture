package com.example.madesubmission.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madesubmission.core.R
import com.example.madesubmission.core.domain.model.Movie
import com.example.madesubmission.core.utils.GlideMovie
import kotlinx.android.synthetic.main.item_list.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder>()  {

    private val lists = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(list: List<Movie>){
        lists.clear()
        lists.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie){
            with(itemView){
                Glide.with(itemView.context)
                    .load(GlideMovie.createImagePath(movie.imagePath))
                    .centerInside()
                    .into(itemImage)
                itemTitle.text = movie.title
                itemReleaseDate.text = resources.getString(R.string.release_date, movie.releaseDate)
                itemOverview.text = movie.overView
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(lists[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.bind(lists[position])

    override fun getItemCount(): Int = lists.size
}