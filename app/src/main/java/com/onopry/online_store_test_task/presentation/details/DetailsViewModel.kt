package com.onopry.online_store_test_task.presentation.details

import androidx.lifecycle.viewModelScope
import com.onopry.domain.models.details.ProductDetails
import com.onopry.domain.usecases.GetProductDetailsUseCase
import com.onopry.domain.utils.ApiError
import com.onopry.domain.utils.ApiException
import com.onopry.domain.utils.ApiSuccess
import com.onopry.online_store_test_task.presentation.base.BaseUiState
import com.onopry.online_store_test_task.presentation.base.BaseViewModel
import com.onopry.online_store_test_task.presentation.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getDetailsUseCase: GetProductDetailsUseCase) :
    BaseViewModel<ProductDetails>() {

    init {
        fetchData()
    }

    override fun fetchData() {
        viewModelScope.launch {
            when (val response = withContext(Dispatchers.IO) { getDetailsUseCase() }) {
                is ApiSuccess -> {
                    _uiState.value = BaseUiState(
                        data = response.data
                    )
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
        _uiState.value = BaseUiState(isLoading = true)
        fetchData()
    }
}