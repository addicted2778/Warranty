package com.warranty.ui.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.warranty.R
import com.warranty.base.DataManager
import com.warranty.databinding.ActivityLoginBinding
import com.warranty.ui.login.viewmodel.LoginRepository
import com.warranty.ui.login.viewmodel.LoginViewModelFactory
import com.warranty.base.BaseActivityNew
import com.warranty.ui.login.viewmodel.LoginViewModel

class LoginActivity : BaseActivityNew<ActivityLoginBinding, LoginViewModel>() {

    lateinit var mContext: Context
    lateinit var mActivity: Activity
    val TAG = this.javaClass.name

    override fun createViewModel(): LoginViewModel {
        val mobileInputRepository: LoginRepository = DataManager.getInstance().mobileInputRepository
        val factory = LoginViewModelFactory(mobileInputRepository)
        return ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this@LoginActivity
        mActivity = this@LoginActivity

        viewModel.setContext(mContext, mActivity)

        //  setViews()

        // observeViewModel()
        setListeners()

    }


    fun setListeners() {
        binding.etMobileNumber.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEND) {
                    doSubmitMobileNo()
                    return true
                }
                return false
            }
        })

        binding.btnSubmit.setOnClickListener {
            doSubmitMobileNo()
        }
    }


    private fun doSubmitMobileNo() {

        val mobileNumber = binding.etMobileNumber.text.toString().trim { it <= ' ' }

        if (mobileNumber.isNullOrEmpty()) {
            binding.etMobileNumber.setError(mContext.getString(R.string.please_input_mobile_no))
        } else {

            viewModel.signUp(
                mobileNumber
            )
        }


    }
}