package com.warranty.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivityNew<BINDING : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: BINDING
    protected abstract fun createViewModel(): VM
    protected abstract fun createViewBinding(layoutInflater: LayoutInflater): BINDING
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createViewBinding(LayoutInflater.from(this))
        setContentView(binding!!.root)
        viewModel = createViewModel()
    }


}