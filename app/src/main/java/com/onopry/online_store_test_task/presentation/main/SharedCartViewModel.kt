package com.onopry.online_store_test_task.presentation.main

import androidx.lifecycle.viewModelScope
import com.onopry.domain.models.cart.Cart
import com.onopry.domain.usecases.GetCartUseCase
import com.onopry.domain.utils.ApiError
import com.onopry.domain.utils.ApiException
import com.onopry.domain.utils.ApiSuccess
import com.onopry.online_store_test_task.presentation.base.BaseUiState
import com.onopry.online_store_test_task.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SharedCartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase
) : BaseViewModel<Cart>() {

    private val _cartItemsCount = MutableStateFlow(0)
    val cartItemsCount = _cartItemsCount.asStateFlow()

    init {
        fetchData()
    }

    override fun fetchData() {
        viewModelScope.launch {
            when (val response = withContext(Dispatchers.IO) { getCartUseCase() }) {
                is ApiSuccess -> {
                    _uiState.value = BaseUiState(
                        data = response.data
                    )
                    _cartItemsCount.value = response.data.basket.size
                }
                is ApiError -> {
                    _uiState.value =
                        BaseUiState(errorMessage = response.message ?: "Unknown Error")
                }
                is ApiException -> {
                    _uiState.value =
                        BaseUiState(errorMessage = response.exception.message ?: "Unknown Error")
                }
            }
        }
    }

    override fun refreshData() {
        fetchData()
    }
}