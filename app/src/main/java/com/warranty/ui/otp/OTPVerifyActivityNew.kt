package com.warranty.ui.otp

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.ViewModelProvider
import com.warranty.R
import com.warranty.base.BaseActivityNew
import com.warranty.base.DataManager
import com.warranty.databinding.ActivityOtpVerificationBinding
import com.warranty.ui.otp.viewmodel.OTPVerifyModelFactory
import com.warranty.ui.otp.viewmodel.OTPVerifyRepository
import com.warranty.ui.otp.viewmodel.OTPVerifyViewModel
import com.warranty.utills.CommonFunctions
import java.text.DecimalFormat
import java.text.NumberFormat


class OTPVerifyActivityNew : BaseActivityNew<ActivityOtpVerificationBinding, OTPVerifyViewModel>(), OnClickListener {

    lateinit var mContext: Context
    lateinit var mActivity: Activity
    val TAG = this.javaClass.name

    private var phoneNumber = ""


    override fun createViewModel(): OTPVerifyViewModel {
        val repository: OTPVerifyRepository = DataManager.getInstance().otpVerifyRepository
        val factory = OTPVerifyModelFactory(repository)
        return ViewModelProvider(this, factory).get(OTPVerifyViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater): ActivityOtpVerificationBinding {
        return ActivityOtpVerificationBinding.inflate(layoutInflater)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@OTPVerifyActivityNew
        mActivity = this@OTPVerifyActivityNew


        viewModel.setContext(mContext, mActivity)
        setViews()
        observeViewModel()
        setListeners()
        initCountDownTimer()

    }

    private fun setViews() {



        if (intent.hasExtra("phoneNumber")) {
            phoneNumber = intent.getStringExtra("phoneNumber")!!
        }


        binding.textViewOTPText!!.text = mContext!!.resources.getString(R.string.enter_the_code_we_sent, "$phoneNumber")


    }

    private fun observeViewModel() {


        viewModel.getErrorLiveData().observe(this) {

            binding.etOtp.error = it
            binding.etOtp.requestFocus()

        }



        viewModel.getEmptyLiveData().observe(this) {
            binding.etOtp.setText(it)
        }
    }

    private fun setListeners() {

        binding.btnSubmit.setOnClickListener(this)
        binding.imgBack.setOnClickListener(this)
        binding.textViewResendOTP.setOnClickListener(this)

        binding.etOtp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().length == 6) {
                    doSubmitOTP()
                }
            }
        })


    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.btn_submit -> doSubmitOTP()

            R.id.textViewResendOTP -> {
                if (binding.textViewResendOTP.text == mContext!!.resources.getString(R.string.resend_otp)) {
                    binding.etOtp.setText("")
                    viewModel.signUp(phoneNumber, "1")
                }
            }


            R.id.img_back -> {
                onBackPressed()
            }



        }
    }


    fun hideKeyboard() {
        CommonFunctions.hideKeyBoard(mActivity, binding.etOtp)
    }

    private fun doSubmitOTP() {

        val otp = binding.etOtp.text.toString().trim()

        viewModel.checkValidation(otp,phoneNumber)
    }


    private fun initCountDownTimer() {

        val numberFormat: NumberFormat = DecimalFormat("00")

        object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.textViewResendOTP.text = "00:${numberFormat.format(millisUntilFinished / 1000)}"
                binding.textViewResendOTP!!.paintFlags = binding.textViewResendOTP!!.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
            }

            override fun onFinish() {
                binding.textViewResendOTP!!.text = mContext!!.resources.getString(R.string.resend_otp)
                binding.textViewResendOTP!!.paintFlags = binding.textViewResendOTP!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            }
        }.start()
    }



}