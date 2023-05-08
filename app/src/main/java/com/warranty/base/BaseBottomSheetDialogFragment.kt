package com.warranty.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.warranty.R

abstract class BaseBottomSheetDialogFragment<BINDING : ViewBinding> : BottomSheetDialogFragment() {

    protected lateinit var binding: BINDING

    protected abstract fun createViewBinding(layoutInflater: LayoutInflater): BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = createViewBinding(inflater)
        val root = binding.root
        return root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)
            }
        }
    }

    override fun getTheme(): Int = R.style.Theme_NoWiredStrapInNavigationBar

}