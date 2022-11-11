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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    init {
        fetchHome()
    }

    fun fetchHome() {
        viewModelScope.launch {
            when (val response = withContext(Dispatchers.IO) { getBannersAndProductsUseCase() }) {
                is ApiSuccess -> {
                    _uiState.value = HomeUiState(
                        banners = response.data.banners,
                        products = response.data.products
                    )
                }
                is ApiError -> {
                    _uiState.value =
                        HomeUiState(errorMessage = response.message ?: "Unknown Error")
                }
                is ApiException -> {
                    _uiState.value =
                        HomeUiState(errorMessage = response.exception.message ?: "Unknown Error")
                }
            }

        }
    }

    fun refresh(){
        _uiState.value = HomeUiState(isLoading = true)
        fetchHome()
    }

    fun getCategories() = getCategoriesUseCase()
}