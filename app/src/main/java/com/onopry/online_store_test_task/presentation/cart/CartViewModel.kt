package com.onopry.online_store_test_task.presentation.cart

import androidx.lifecycle.ViewModel
import com.onopry.domain.usecases.GetCartUseCase

class CartViewModel(
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {

}