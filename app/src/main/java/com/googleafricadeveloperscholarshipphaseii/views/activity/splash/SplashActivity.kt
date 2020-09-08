package com.googleafricadeveloperscholarshipphaseii.views.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.googleafricadeveloperscholarshipphaseii.R
import com.googleafricadeveloperscholarshipphaseii.databinding.SplashMainBinding
import com.googleafricadeveloperscholarshipphaseii.views.activity.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private lateinit var splashActivity: SplashMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initSplash()
    }

    //region init
    private fun initDataBinding() {
        splashActivity = DataBindingUtil.setContentView(this, R.layout.splash_main)
        splashActivity.lifecycleOwner = this
    }

    private fun initSplash() {
        handler = Handler()
        runnable = Runnable {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        handler!!.postDelayed(runnable!!, 3000)
    }

    //endregion

    override fun onDestroy() {
        super.onDestroy()
        handler!!.removeCallbacks(runnable!!)
    }
}