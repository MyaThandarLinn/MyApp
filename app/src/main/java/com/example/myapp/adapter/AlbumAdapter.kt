package com.example.myapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.AlbumDetail
import com.example.myapp.R
import com.example.myapp.modals.AlbumModal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_row.view.*

class AlbumAdapter (val context : Context, val albums : List<AlbumModal>) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    companion object{
        val ALBUM_KEY = "ALBUM_KEY"
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.album_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albums[position]

        holder.itemView.tvTitle.text = album.title.substring(0,10)
        Picasso.get().load(album.thumbnailUrl).into(holder.itemView.albumImg)
        val first : List<String>  = album.url.split("://")
        val second : List<String> = first[1].split(".")
        holder.itemView.tvDetail.text = second[1]

        holder.itemView.btnShow.setOnClickListener {
            val intent = Intent(context, AlbumDetail::class.java)
            intent.putExtra(ALBUM_KEY,album)
            context.startActivity(intent)
        }
    }
}