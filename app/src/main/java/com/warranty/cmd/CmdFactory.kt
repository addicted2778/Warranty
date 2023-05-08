package com.warranty.cmd

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.provider.Settings
import com.warranty.BuildConfig
import com.warranty.R
import org.json.JSONException
import org.json.JSONObject

class CmdFactory(private val mContext: Context) {

    private val deviceType = "android"
    private var APIVERSION = "10.0"
    private val AUTHKEY = "cd5b5206057d79a8bcf5656960606a4a8b53e137c15eba5a96a17830a52ebba9"
    private var APPVERSION = "1"
    private lateinit var params: HashMap<String, String>
    private var deviceId: String = ""
    private val mPref: SharedPreferences
    private var uniqueID = ""


    init {
        mPref = mContext!!.getSharedPreferences(
            mContext.resources.getString(R.string.pref_name),
            Activity.MODE_PRIVATE
        )
        // deviceId = AppData.getString(AppData.globalPref_fcm, mContext, Constant.DEVICE_TOKEN)
        try {
            APPVERSION = mContext.packageManager.getPackageInfo(
                mContext.packageName,
                0
            ).versionCode.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        uniqueID = Settings.Secure.getString(
            mContext.contentResolver,
            Settings.Secure.ANDROID_ID
        )


    }


    private fun createDefaultObject(): HashMap<String, String> {
        params = HashMap()
        try {
            `params`!!.put("deviceType", deviceType)
            `params`!!.put("appVersion", APPVERSION)
            `params`!!.put("apiVersion", APIVERSION)
            `params`!!.put("authKey", AUTHKEY)
            `params`!!.put("deviceId", deviceId)
            `params`!!.put("deviceUniqueId", uniqueID)
            if (BuildConfig.DEBUG)
                `params`!!.put("isSigned", "0")
            else
                `params`!!.put("isSigned", "1")

            /*if (AppData.getBoolean(mContext, Constant.isLogin)) {
                `object`!!.put("userId", AppData.getString(mContext, Constant.USER_ID))

                if (!MyApplication.isConnectedWithStagingV2) {
                    `object`!!.put("accessToken", AppData.getString(mContext, Constant.ACCESS_TOKEN))
                }
            }

            if (AppData.getInt(mContext!!, Constant.SUDO_LOGIN) == 1) {
                `object`!!.put("sudologin", "1")
            }*/

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return `params`!!
    }


    fun signupMobileCMD(vararg args: String): HashMap<String, String> {
        `params` = createDefaultObject()
        try {
            `params`!!.put("phone_number", args[0])

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return params
    }

    fun signupSubmitOTPCMD(vararg args: String): HashMap<String, String> {
        `params` = createDefaultObject()
        try {
            `params`!!.put("phone_number", args[0])
            `params`!!.put("otp", args[1])

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return params
    }


}