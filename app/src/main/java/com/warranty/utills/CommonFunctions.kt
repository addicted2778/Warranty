package com.warranty.utills

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

object CommonFunctions {


    fun hideKeyBoard(mActivity: Activity, view: View) {
        val imm = mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}