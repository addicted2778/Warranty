package com.warranty.ui.login.viewmodel

import android.app.Activity
import android.content.Context
import com.warranty.controller.WebserviceInterface

interface LoginRepository {

    fun signup(
        mActivity: Activity,
        mContext: Context,
        webserviceInterface: WebserviceInterface,
        param: HashMap<String, String>
    )
}