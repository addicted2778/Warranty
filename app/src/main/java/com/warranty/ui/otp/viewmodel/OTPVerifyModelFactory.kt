
package com.warranty.ui.otp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class OTPVerifyModelFactory(private val repository: OTPVerifyRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OTPVerifyViewModel::class.java)) {
            return OTPVerifyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}