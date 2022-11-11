package com.onopry.online_store_test_task.presentation.details

import androidx.lifecycle.ViewModel
import com.onopry.domain.usecases.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getDetails: GetProductDetailsUseCase) :
    ViewModel() {

}