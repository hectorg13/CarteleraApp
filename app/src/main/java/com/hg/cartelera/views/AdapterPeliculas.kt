package com.hg.cartelera.views

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hg.cartelera.R
import com.hg.cartelera.core.Constants
import com.hg.cartelera.network.PeliculaModel

class AdapterPeliculas(val context: Context, var listaPeliculas: List<PeliculaModel>) :
    RecyclerView.Adapter<AdapterPeliculas.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvPelicula = itemView.findViewById(R.id.cvPelicula) as CardView
        val ivPoster = itemView.findViewById(R.id.ivPoster) as ImageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_peliculas, parent, false)
        Log.i("ViewHolder", "Created ")
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = listaPeliculas[position]

        Glide
            .with(context)
            .load("${Constants.BASE_URL_IMAGEN}${pelicula.poster}")
            .apply(RequestOptions().override(Constants.IMAGEN_ANCHO, Constants.IMAGEN_ALTO))
            .into(holder.ivPoster)
        Log.i("onBiendViewHolder", "Implemented")
    }

    override fun getItemCount(): Int {
        return listaPeliculas.size
    }
}