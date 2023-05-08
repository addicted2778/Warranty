
package com.warranty.ui.otp.viewmodel

import android.app.Activity
import android.content.Context
import com.warranty.controller.WebserviceInterface
import com.warranty.webservices.APIExecutor


object OTPVerifyRepositoryImpl : OTPVerifyRepository {
    public var instance: OTPVerifyRepositoryImpl? = null
        get() {
            if (field == null) {
                field = OTPVerifyRepositoryImpl
            }
            return field
        }
        private set

    override fun submitOTP(mActivity: Activity, mContext: Context, webserviceInterface: WebserviceInterface, param: HashMap<String, String>) {

        val apiExecutor = APIExecutor(mActivity, mContext, webserviceInterface)
        apiExecutor.submitOTP(param)

    }

    override fun signup(mActivity: Activity, mContext: Context, webserviceInterface: WebserviceInterface, param: HashMap<String, String>) {
        val apiExecutor = APIExecutor(mActivity, mContext, webserviceInterface)
       // apiExecutor.loginWithMobile(param)
    }

    override fun changeLanguage(mActivity: Activity, mContext: Context, webserviceInterface: WebserviceInterface, param: HashMap<String, String>) {
        val apiExecutor = APIExecutor(mActivity, mContext, webserviceInterface)
       // apiExecutor.changeLanguage(param)
    }
}