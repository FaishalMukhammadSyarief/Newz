package com.zhalz.newz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.crocodic.core.data.CoreSession
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zhalz.newz.R
import com.zhalz.newz.databinding.BottomSheetBinding

class BottomSheet(private val session: CoreSession, private val result: () -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}