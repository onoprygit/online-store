package com.onopry.online_store_test_task.presentation.home

import androidx.lifecycle.viewModelScope
import com.onopry.domain.models.home.BannerAndProduct
import com.onopry.domain.usecases.GetBannersAndProductsUseCase
import com.onopry.domain.usecases.GetProductCategoriesUseCase
import com.onopry.domain.utils.ApiError
import com.onopry.domain.utils.ApiException
import com.onopry.domain.utils.ApiSuccess
import com.onopry.online_store_test_task.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBannersAndProductsUseCase: GetBannersAndProductsUseCase,
    private val getCategoriesUseCase: GetProductCategoriesUseCase
) : BaseViewModel<BannerAndProduct>() {
    /*private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()*/

    init {
        fetchData()
    }

    override fun refreshData() {
        _uiState.value = HomeUiState(isLoading = true)
        fetchData()
    }

    fun getCategories() = getCategoriesUseCase()


    override fun fetchData() {
        viewModelScope.launch {
            when (val response = withContext(Dispatchers.IO) { getBannersAndProductsUseCase() }) {
                is ApiSuccess -> {
                    _uiState.value = HomeUiState(
                        data = response.data
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
}