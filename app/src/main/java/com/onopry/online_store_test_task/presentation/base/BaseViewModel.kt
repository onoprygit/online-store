package com.onopry.online_store_test_task.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T>(): ViewModel() {

    protected val _uiState = MutableStateFlow(BaseUiState<T>(isLoading = true))
    val uiState = _uiState.asStateFlow()

    abstract fun refreshData()
    protected abstract fun fetchData()
}