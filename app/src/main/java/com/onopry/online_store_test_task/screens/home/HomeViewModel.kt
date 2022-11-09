package com.onopry.online_store_test_task.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onopry.domain.usecases.GetBannersAndProductsUseCase
import com.onopry.domain.usecases.GetProductCategoriesUseCase
import com.onopry.domain.utils.ApiError
import com.onopry.domain.utils.ApiException
import com.onopry.domain.utils.ApiSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBannersAndProductsUseCase: GetBannersAndProductsUseCase,
    private val getCategoriesUseCase: GetProductCategoriesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    fun fetchHome() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) { getBannersAndProductsUseCase() }
            when (response) {
                is ApiSuccess -> {
                    _uiState.emit(
                        HomeUiState(
                            banners = response.data.banners,
                            products = response.data.products
                        )
                    )
                }
                is ApiError -> {
                    _uiState.emit(
                        HomeUiState(
                            errorMessage = response.message ?: "Unknown Error"
                        )
                    )
                }
                is ApiException -> {
                    _uiState.emit(
                        HomeUiState(
                            errorMessage = response.exception.message ?: "Unknown Error"
                        )
                    )
                }
            }

        }
    }

    fun getCategories() = getCategoriesUseCase()
}