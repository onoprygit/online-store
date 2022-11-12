package com.onopry.online_store_test_task.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OffsetItemDecorator(
    private val verticalOffset: Int,
    private val horizontalOffset: Int
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect){
            bottom = verticalOffset
            top = verticalOffset
            left = horizontalOffset
            right = horizontalOffset
        }
    }
}