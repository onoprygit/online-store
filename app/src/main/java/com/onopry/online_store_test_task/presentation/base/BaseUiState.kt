package com.onopry.online_store_test_task.presentation.base

open class BaseUiState<T>(
    open val isLoading: Boolean = false,
    open val data: T? = null,
    open val errorMessage: String = ""
)