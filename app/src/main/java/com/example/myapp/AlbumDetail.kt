package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.myapp.adapter.AlbumAdapter
import com.example.myapp.modals.AlbumModal
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_detail.*
import org.jetbrains.anko.toast

class AlbumDetail : AppCompatActivity() {
    lateinit var dAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_detail)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.title = "Album Detail"

        MobileAds.initialize(this,getString(R.string.admob_app_id))
        dAdView =  findViewById(R.id.adDeView)
        val adRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()
        dAdView.loadAd(adRequest)


        val album = intent.getParcelableExtra<AlbumModal>(AlbumAdapter.ALBUM_KEY)
        toast("Image is ${ album.url}")

        tvDeAlbumTitle.text = album.title
        tvDeAlbumId.text = album.albumId.toString()
        tvDeId.text = album.id.toString()
        tvDeThumb.text = album.thumbnailUrl

        Picasso.get().load(album.url).into(deAlbumImg)

    }
}