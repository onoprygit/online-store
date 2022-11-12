package com.onopry.online_store_test_task.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.onopry.online_store_test_task.R

class FilterBottomSheet: BottomSheetDialogFragment(R.layout.bottom_sheet_filter) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val closeButton: ImageButton = view.findViewById(R.id.closeButton)
        val acceptButton: Button = view.findViewById(R.id.doneButton)

        closeButton.setOnClickListener { findNavController().popBackStack() }
        acceptButton.setOnClickListener { findNavController().popBackStack() }
    }
}