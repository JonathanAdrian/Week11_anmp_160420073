package com.example.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.model.Movie

class MovieListAdapter (val movieList:ArrayList<Movie>)
    :RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(){
        class MovieViewHolder(v: View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var txtIdMovie = holder.itemView.findViewById<TextView>(R.id.txtID)
        var txtTitle = holder.itemView.findViewById<TextView>(R.id.txtName)
        var txtReleaseYear = holder.itemView.findViewById<TextView>(R.id.txtReleaseYear)
        var txtGenre = holder.itemView.findViewById<TextView>(R.id.txtGenre)
        var txtActor = holder.itemView.findViewById<TextView>(R.id.txtActor)
        var txtActor1 = holder.itemView.findViewById<TextView>(R.id.txtActor1)
        var txtActor2 = holder.itemView.findViewById<TextView>(R.id.txtActor2)
        var txtDirector = holder.itemView.findViewById<TextView>(R.id.txtDirector)
        var id = movieList[position].id
        var title = movieList[position].title
        var release_year = movieList[position].release_year
        var genre = movieList[position].genre
        var actor = movieList[position].actors
        var director = movieList[position].director

        txtIdMovie.text = "ID: $id"
        txtTitle.text = "$title"
        txtReleaseYear.text = "$release_year"
        txtGenre.text = "Genre : $genre"
        txtActor.text = "${actor?.get(0)}"
        txtActor1.text = "${actor?.get(1)}"
        txtActor2.text = "${actor?.get(2)}"
        txtDirector.text = "Director: ${director?.name} (${director?.nationality})"
    }

    fun updateMovieList(newMovieList: ArrayList<Movie>){
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}