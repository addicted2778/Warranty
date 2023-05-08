package com.warranty.webservices

import android.app.Activity
import android.content.Context
import com.warranty.MyApplication
import com.warranty.R
import com.warranty.controller.WebserviceInterface
import com.warranty.database.AppData
import com.warranty.utills.ConnectionDetector
import com.warranty.utills.Constant
import org.json.JSONException
import org.json.JSONObject
import sg.whyq.storemanager.webservices.WebserviceAPIRequestCode
import sg.whyqokhttp.Logs
import sg.whyqokhttp.Method
import sg.whyqokhttp.ResponseListener
import sg.whyqokhttp.WebServiceExecutor

class APIExecutor(
    var mActivity: Activity,
    var mContext: Context,
    webserviceInterface: WebserviceInterface
) {
    var webserviceInterface: WebserviceInterface

    init {
        this.webserviceInterface = webserviceInterface
    }

    fun signup(params: HashMap<String, String>) {

        sendDataOnServer(
            params,
            Method.POST,
            MyApplication.SIGNUP_URL,
            true,
            WebserviceAPIRequestCode.LOGIN
        )

    }

    fun submitOTP(params: HashMap<String, String>) {

        sendDataOnServer(
            params,
            Method.POST,
            MyApplication.SUBMIT_OTP,
            true,
            WebserviceAPIRequestCode.SUBMIT_OTP
        )

    }


    fun sendDataOnServer(
        param: HashMap<String, String>,
        requestMethod: Int,
        url: String,
        showProgressDialogue: Boolean,
        requestCode: Int

    ) {
        if (ConnectionDetector.isConnectingToInternet(mContext)) {
            val headers = HashMap<String, String>()

            if (AppData.getBoolean(mContext, Constant.isLogin)) {
                headers.put("authorization", AppData.getString(mContext, Constant.ACCESS_TOKEN)!!)
            }


            val executor = WebServiceExecutor(mContext)
            executor.setRequestParam(param)
            executor.setHeader(headers)
            executor.isProgressDialogShow(showProgressDialogue)
            executor.setUrl(url)
            executor.setRequestMethod(requestMethod)
            executor.setRequestCode(requestCode)
            executor.setResponseListener(object : ResponseListener {
                override fun onResponse(requestCode: Int, responseCode: Int, response: String) {
                    Logs.e("", "Response  : $response")
                    if (Constant.checkResponseAuthenticate(response, mContext, mActivity)) {
                        webserviceInterface.onSuccess(requestCode, response)
                    } else {
                        try {
                            val mainObject = JSONObject(response)
                            if (mainObject.optInt("status") == 2) {
                            } else {
                                //  sendError(response, param.toString(), url);
                            }
                        } catch (e: JSONException) {
                            //  sendError(response, param.toString(), url);
                        }
                    }
                }

                override fun onFailed(requestCode: Int) {

                    webserviceInterface.onFailure(requestCode)
                    // sendError("", param.toString(), url);
                }
            })
            executor.execute()
        } else {
            Constant.showSnackBar(mActivity, mContext.getString(R.string.check_internet_connection))
        }
    }
}