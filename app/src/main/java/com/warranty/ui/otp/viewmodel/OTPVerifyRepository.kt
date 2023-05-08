package com.warranty.ui.otp.viewmodel

import android.app.Activity
import android.content.Context
import com.warranty.controller.WebserviceInterface

interface OTPVerifyRepository {


    fun submitOTP(mActivity: Activity, mContext: Context, webserviceInterface: WebserviceInterface, param: HashMap<String, String>)

    fun signup(mActivity: Activity, mContext: Context, webserviceInterface: WebserviceInterface, param: HashMap<String, String>)

    fun changeLanguage(mActivity: Activity, mContext: Context, webserviceInterface: WebserviceInterface, param: HashMap<String, String>)

}