package com.onopry.online_store_test_task.screens.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.databinding.FragmentHomeBinding
import com.onopry.online_store_test_task.screens.adapters.CategoryAdapter
import com.onopry.online_store_test_task.screens.adapters.ProductsAdapter
import com.onopry.online_store_test_task.utils.shortToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        val categoryAdapter = CategoryAdapter { categoryId ->
            shortToast(categoryId.toString())
        }
        categoryAdapter.setCategoriesList(viewModel.getCategories())
        binding.categoriesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoriesRecycler.adapter = categoryAdapter

        val productsAdapter = ProductsAdapter { productId ->
            shortToast(productId.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    with(viewModel.uiState.value){
                        if (isLoading) Log.d("TAG_" + this.javaClass.simpleName, "Data is loading!")
                        productsAdapter.setProductsList(this.products)
                        if (errorMessage.isNotEmpty()) shortToast(errorMessage)
                    }
                }
            }
        }

        binding.productsRecycler.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.productsRecycler.adapter = productsAdapter
    }
}