package com.onopry.online_store_test_task.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.shortToast(message: String){
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(message: String){
    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}