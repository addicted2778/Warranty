package com.warranty.controller

interface WebserviceInterface {
    fun onSuccess(requestCode: Int, responseStr: String)
    fun onFailure(requestCode: Int)
}