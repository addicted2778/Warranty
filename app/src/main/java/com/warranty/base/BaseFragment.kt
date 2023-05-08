package com.warranty.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<BINDING : ViewBinding, VM : BaseViewModel> : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: BINDING

    protected abstract fun createViewModel(): VM

    protected abstract fun createViewBinding(layoutInflater: LayoutInflater): BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = createViewBinding(inflater)
        val root = binding!!.root
        viewModel = createViewModel()
        return root
    }
}