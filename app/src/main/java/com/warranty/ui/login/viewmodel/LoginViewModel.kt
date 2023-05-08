package com.warranty.ui.login.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.warranty.R
import com.warranty.base.BaseViewModel
import com.warranty.cmd.CmdFactory
import com.warranty.controller.WebserviceInterface
import com.warranty.ui.otp.OTPVerifyActivityNew
import com.warranty.utills.Constant
import org.json.JSONObject
import sg.whyq.storemanager.webservices.WebserviceAPIRequestCode


class LoginViewModel(var mobileInputRepository: LoginRepository) : BaseViewModel() {


    lateinit var mActivity: Activity
    lateinit var mContext: Context

    private val apiCallBack = APICallBack()

    var mobile = ""

    fun setContext(mContext: Context, mActivity: Activity) {
        this.mContext = mContext
        this.mActivity = mActivity
    }


    fun signUp(mobile: String) {

        this.mobile = mobile

        val cmdFactory = CmdFactory(mContext)
        val params = cmdFactory.signupMobileCMD(mobile)

        mobileInputRepository.signup(mActivity, mContext, apiCallBack, params)

    }

    inner class APICallBack : WebserviceInterface {

        override fun onSuccess(requestCode: Int, responseStr: String) {
            when (requestCode) {
                WebserviceAPIRequestCode.LOGIN -> manageOtpResponse(responseStr)
                // WebserviceAPIRequestCode.LOGIN -> manageOtpSuccessResponse(responseStr, WebserviceAPIRequestCode.LOGIN)
                else -> {
                }
            }
        }

        override fun onFailure(requestCode: Int) {
            Constant.showSnackBar(mActivity, mContext.getString(R.string.server_not_responding))
        }

    }


    private fun manageOtpResponse(responeStr: String) {
        try {
            val mainObject = JSONObject(responeStr)
            val status = mainObject.getString("status").toInt()
            val message = mainObject.optString("message")


            if (status == 1) {


                val otp_screen = mainObject.getJSONObject("data").optInt("otp_screen")

                if (otp_screen == 1) {


                    val intent = Intent(mContext, OTPVerifyActivityNew::class.java)
                    intent.putExtra("phoneNumber", mobile)
                    mActivity.startActivity(intent)

                }

            } else {


                Constant.showDefaultAlert(mContext, message, false)

            }
        } catch (e: Exception) {
            Constant.showSnackBar(mActivity, mContext.getString(R.string.server_not_responding))
        }
    }

}