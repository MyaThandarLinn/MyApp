package com.example.myapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var mAdView : AdView
    lateinit var bounceAnim : Animation
    lateinit var zoomAnim  : Animation
    lateinit var flipAnim : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        MobileAds.initialize(this, getString(R.string.admob_app_id))
        mAdView = findViewById(R.id.adView)
         val adRequest = AdRequest.Builder().addTestDevice("00A340F7D1606FCCFD5C3FADA7522422").build() // this is for mobile device
        // val adRequest = AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build() // this is for emulator use
       // val adRequest = AdRequest.Builder().build()  // this is upload to playstore .So , we don't need device
        mAdView.loadAd(adRequest )

        bounceAnim = AnimationUtils.loadAnimation(this,R.anim.bounce)
        appLogo.animation = bounceAnim

        zoomAnim = AnimationUtils.loadAnimation(this,R.anim.zoom)
      //  tvSlogan.animation = zoomAnim

        flipAnim = AnimationUtils.loadAnimation(this,R.anim.flip)
       // startButton.animation  = flipAnim

        bounceAnim.setAnimationListener(this)
        zoomAnim.setAnimationListener(this)

        startButton.setOnClickListener {
            val intent = Intent(this,AlbumList::class.java)
            startActivity(intent)
        }
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }

    override fun onAnimationEnd(p0: Animation?) {
        if(p0 == bounceAnim){
            tvSlogan.visibility = View.VISIBLE
            tvSlogan.animation = zoomAnim
        }else if (p0 == zoomAnim){
            if(checkConnect()){
                startButton.visibility = View.VISIBLE
                startButton.animation = flipAnim
            }else{
                alert("Your device need internet connection", "Connection Problem!"){
                    yesButton{toast("Try Again !")}
                    noButton{toast("You can't use our app")}
                }.show()
                progressBar.visibility = View.VISIBLE
            }

        }
    }

    override fun onAnimationStart(p0: Animation?) {
    }

    fun checkConnect(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork : NetworkInfo?= connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}