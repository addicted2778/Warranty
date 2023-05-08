package com.warranty.ui.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.warranty.R
import com.warranty.ui.dashboard.DashboardActivity
import com.warranty.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    lateinit var mContext: Context
    lateinit var mActivity: Activity
    val TAG = this.javaClass.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mContext = this@SplashActivity
        mActivity = this@SplashActivity

        //val intent = Intent(mContext, LoginActivity::class.java)
        val intent = Intent(mContext, DashboardActivity::class.java)
        startActivity(intent)
    }
}