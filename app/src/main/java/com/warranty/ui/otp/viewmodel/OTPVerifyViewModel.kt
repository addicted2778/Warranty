package com.warranty.ui.otp.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.warranty.R
import com.warranty.base.BaseViewModel
import com.warranty.cmd.CmdFactory
import com.warranty.controller.WebserviceInterface
import org.json.JSONObject

import com.warranty.ui.otp.OTPVerifyActivityNew
import com.warranty.utills.Constant

import sg.whyq.storemanager.webservices.WebserviceAPIRequestCode

class OTPVerifyViewModel(var repository: OTPVerifyRepository) : BaseViewModel() {


    val showErrorLiveData = MutableLiveData<String>()
    val setEmptyLiveData = MutableLiveData<String>()

    lateinit var mActivity: Activity
    lateinit var mContext: Context
    var phoneNumber = ""
    private val apiCallback = APICallback()

    fun getErrorLiveData(): LiveData<String?> {
        return showErrorLiveData
    }


    fun getEmptyLiveData(): LiveData<String?> {
        return setEmptyLiveData
    }


    fun setContext(mContext: Context, mActivity: Activity) {
        this.mContext = mContext
        this.mActivity = mActivity
    }

    fun checkValidation(otp: String, phoneNumber: String) {

        this.phoneNumber = phoneNumber

        if (otp.isEmpty()) {
            showErrorLiveData.postValue(mContext.getString(R.string.error_empty_fields))
        } else if (otp.length != 6) {
            showErrorLiveData.postValue(mContext.getString(R.string.invalid_otp))
        } else {
            (mActivity as OTPVerifyActivityNew).hideKeyboard()
            submitOTP(otp)
        }


    }


    fun signUp(mobile: String, resendOtp: String) {
        this.phoneNumber = mobile
        val cmdFactory = CmdFactory(mContext)
        val params = cmdFactory.signupMobileCMD(mobile, resendOtp)
        repository.signup(mActivity, mContext, apiCallback, params)

    }

    private fun submitOTP(otp: String) {
        val cmdFactory = CmdFactory(mContext)
        val params = cmdFactory.signupSubmitOTPCMD(phoneNumber, otp)
        repository.submitOTP(mActivity, mContext, apiCallback, params)

    }


    inner class APICallback : WebserviceInterface {

        override fun onSuccess(requestCode: Int, responseStr: String) {
            when (requestCode) {
                WebserviceAPIRequestCode.SUBMIT_OTP -> manageSubmitOtpResponse(responseStr)
                //WebserviceAPIRequestCode.SUBMIT_OTP_REQUEST -> manageOtpSuccessResponse(responseStr, WebserviceAPIRequestCode.LOGIN)
                else -> {
                }
            }
        }

        override fun onFailure(requestCode: Int) {
            Constant.showSnackBar(mActivity, mContext.getString(R.string.server_not_responding))
        }

    }


    private fun manageSubmitOtpResponse(responeStr: String) {
        try {
            val mainObject = JSONObject(responeStr)
            val status = mainObject.getString("status").toInt()
            val message = mainObject.optString("message")
            val resendOtp = mainObject.optInt("resendOtp")


            if (status == 1) {

            } else {
                Constant.showDefaultAlert(mContext, message, false)
            }
        } catch (e: Exception) {
            Constant.showSnackBar(mActivity, mContext.getString(R.string.server_not_responding))
        }
    }
}