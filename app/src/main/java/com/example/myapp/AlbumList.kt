package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapp.adapter.AlbumAdapter
import com.example.myapp.modals.AlbumModal
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_album_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class AlbumList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)
        supportActionBar?.title = "Album List"
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        doAsync{
            val jsons = URL("https://jsonplaceholder.typicode.com/photos").readText()
            uiThread{
                val albums = Gson().fromJson(jsons, Array<AlbumModal>::class.java).toList()

                toast("here is ${albums.size}")
                val layoutManager = GridLayoutManager(this@AlbumList,2)
                albumRecycler.layoutManager = layoutManager

                val  adapter = AlbumAdapter(this@AlbumList,albums)
                albumRecycler.adapter = adapter
            }
        }
    }
}