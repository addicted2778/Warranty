package com.warranty.ui.login.viewmodel

import android.app.Activity
import android.content.Context
import com.warranty.controller.WebserviceInterface
import com.warranty.webservices.APIExecutor

object LoginRepositoryImpl : LoginRepository {

    public var instance: LoginRepositoryImpl? = null
        get() {
            if (field == null) {
                field = LoginRepositoryImpl
            }
            return field
        }
        private set

    override fun signup(
        mActivity: Activity,
        mContext: Context,
        webserviceInterface: WebserviceInterface,
        param: HashMap<String, String>
    ) {

        val apiExecutor = APIExecutor(mActivity, mContext, webserviceInterface)
        apiExecutor.signup(param)
    }

}