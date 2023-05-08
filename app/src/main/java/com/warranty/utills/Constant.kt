package com.warranty.utills

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.warranty.R
import com.warranty.controller.DialogCallBack
import com.warranty.database.AppData
import com.warranty.ui.login.LoginActivity
import org.json.JSONException
import org.json.JSONObject

object Constant {

    var isLogin = "isLogin"
    const val ACCESS_TOKEN = "access_token"



    fun checkResponseAuthenticate(response: String?, mContext: Context, mActivity: Activity?): Boolean {
        return try {
            val mainObject = JSONObject(response)
            if (!mainObject.has("status")) {
                return false
            } else if (mainObject.optInt("status") == 2) {
                mActivity!!.runOnUiThread {
                    getLogoutDialog(mContext, mActivity)
                }
                false
            } else {
                true
            }
        } catch (e: JSONException) {
            showSnackBar(mActivity, mActivity!!.getString(R.string.server_not_responding))
            e.printStackTrace()
            false
        }
    }

    fun showSnackBar(mActivity: Activity?, message: String?) {
        val snackBar = Snackbar.make(
            mActivity!!.findViewById(android.R.id.content),
            message!!,
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction(R.string.txt_dismiss) { snackBar.dismiss() }
        snackBar.show()
    }

    fun getLogoutDialog(mContext: Context, mActivity: Activity) {
        mActivity.runOnUiThread(Runnable {


            showDefaultAlertWithSingleButton(mContext, "", mContext.getString(R.string.message_logout_user_not_found), mContext.getString(R.string.txt_ok), false,
                object : DialogCallBack {
                    override fun onDismiss() {

                        AppData.save(mContext, Constant.isLogin, false)
                        AppData.clearAll(mContext)
                        AppData.clearAll(mContext)
                        val intent = Intent(mContext, LoginActivity::class.java)
                        val cn = intent.component
                        val mainIntent = Intent.makeRestartActivityTask(cn)
                        mContext.startActivity(mainIntent)

                    }
                })
        })
    }

    fun showDefaultAlertWithSingleButton(
        mContext: Context,
        title: String,
        description: String,
        positiveButtonText: String,
        isCancelable: Boolean,
        callBack: DialogCallBack?
    ) {

        val dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(isCancelable)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        dialog.setContentView(R.layout.dialog_default_dialog)

        val imageViewCloseDialog = dialog.findViewById<ImageView>(R.id.imageViewCloseDialog)
        val button = dialog.findViewById<Button>(R.id.buttonOk)
        val buttonCancel = dialog.findViewById<Button>(R.id.buttonCancel)
        val buttonOkSingle = dialog.findViewById<Button>(R.id.buttonOkSingle)

        val textViewTitle = dialog.findViewById<TextView>(R.id.textViewTitle)
        val textViewDescription = dialog.findViewById<TextView>(R.id.textViewDescription)

        button.visibility = View.GONE
        buttonCancel.visibility = View.GONE
        buttonOkSingle.visibility = View.VISIBLE

        button.setText(positiveButtonText)
        buttonOkSingle.setText(positiveButtonText)

        textViewDescription.text = Html.fromHtml(description)
        textViewTitle.text = if (title.isBlank())
            mContext.resources.getString(R.string.app_name)
        else
            title

        if (isCancelable) {
            imageViewCloseDialog.visibility = View.VISIBLE
            imageViewCloseDialog.setOnClickListener {
                dialog.dismiss()
                callBack?.onDismiss()
            }
        } else {
            imageViewCloseDialog.visibility = View.GONE
        }

        buttonOkSingle.setOnClickListener {
            dialog.dismiss()
            callBack?.onDismiss()
        }

        dialog.show()
    }

    fun showDefaultAlert(mContext: Context?, msg: String?, isCancelable: Boolean) {


        showDefaultAlertWithSingleButton(mContext!!, "", msg!!, mContext.getString(R.string.txt_ok), isCancelable, null)
    }
}