package com.warranty.controller

interface DialogCallBack {
    fun onDismiss()
}

interface DialogCallBackYesNo {
    fun onYesClicked()
    fun onNoClicked()
}
