package com.zhalz.newz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.crocodic.core.data.CoreSession
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zhalz.newz.R
import com.zhalz.newz.databinding.BottomSheetBinding

class BottomSheet(private val session: CoreSession, private val result: () -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetBinding

    val filterId = ObservableField(false)
    val filterEn = ObservableField(false)
    val filterAr = ObservableField(false)
    val filterCh = ObservableField(false)
    val filterRu = ObservableField(false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomSheet = this

        filterId.set(session.getBoolean(INDONESIA))
        filterEn.set(session.getBoolean(ENGLISH))
        filterAr.set(session.getBoolean(ARABIC))
        filterCh.set(session.getBoolean(CHINESE))
        filterRu.set(session.getBoolean(RUSSIAN))
    }

    fun apply() {
        session.setValue(INDONESIA, filterId.get() == true)
        session.setValue(ENGLISH, filterEn.get() == true)
        session.setValue(ARABIC, filterAr.get() == true)
        session.setValue(CHINESE, filterCh.get() == true)
        session.setValue(RUSSIAN, filterRu.get() == true)

        result()
        dismiss()
    }

    companion object {
        const val INDONESIA = "id"
        const val ENGLISH = "en"
        const val ARABIC = "ar"
        const val CHINESE = "zh"
        const val RUSSIAN = "ru"

        const val TAG = "bottom-sheet-filter"
    }

}