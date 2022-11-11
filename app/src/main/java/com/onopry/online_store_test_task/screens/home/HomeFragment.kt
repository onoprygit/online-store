package com.onopry.online_store_test_task.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.adapters.BannersAdapter
import com.onopry.online_store_test_task.adapters.CategoryAdapter
import com.onopry.online_store_test_task.adapters.ProductsAdapter
import com.onopry.online_store_test_task.databinding.FragmentHomeBinding
import com.onopry.online_store_test_task.screens.details.DetailsFragmentDirections
import com.onopry.online_store_test_task.utils.gone
import com.onopry.online_store_test_task.utils.hide
import com.onopry.online_store_test_task.utils.shortToast
import com.onopry.online_store_test_task.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        setupListeners()
        setupAdapters()
    }

    private fun setupAdapters() {
        createCategoryAdapter()
        val bannerAdapter = createBannersAdapter()
        val productsAdapter = createProductsAdapter()

        setUpBannerAndProductAdapter(
            productsAdapter,
            bannerAdapter
        )
    }

    private fun refreshData() {
        viewModel.refresh()
    }

    private fun setupListeners() {

        initRefreshListener()
        initFilterListeners()
    }

    private fun initFilterListeners() {
        val filterBottomSheetBehavior =
            BottomSheetBehavior.from(binding.bottomSheetFilter.filterBottomSheet)
        binding.filterBtn.setOnClickListener {
            filterBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.bottomSheetFilter.closeButton.setOnClickListener {
            filterBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun initRefreshListener() {
        binding.refreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                shortToast("REFRESHING")
                refreshData()
                binding.refreshLayout.isRefreshing = false
            }
        }
    }


    private fun createCategoryAdapter() {
        val categoryAdapter = CategoryAdapter { categoryId ->
            shortToast(categoryId.toString())
        }
        categoryAdapter.setCategoriesList(viewModel.getCategories())
        binding.categoriesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoriesRecycler.adapter = categoryAdapter
    }

    private fun createProductsAdapter(): ProductsAdapter {
        val productsAdapter = ProductsAdapter { productId ->
            shortToast("Product id = $productId")
            openDetails(productId)
        }

        binding.productsRecycler.adapter = productsAdapter
        binding.productsRecycler.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        return productsAdapter
    }

    private fun createBannersAdapter(): BannersAdapter {
        val bannerAdapter = BannersAdapter { bannerId -> shortToast("Banner id = $bannerId") }
        binding.bannersPager.adapter = bannerAdapter
        return bannerAdapter
    }

    private fun setUpBannerAndProductAdapter(
        productsAdapter: ProductsAdapter,
        bannerAdapter: BannersAdapter
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    with(viewModel.uiState.value) {
                        showLoadingOrError(this)
                        productsAdapter.setProductsList(this.products)
                        bannerAdapter.setBannerList(this.banners)
                    }
                }
            }
        }
    }

    private fun openDetails(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }

    // todo improve method
    private fun showLoadingOrError(uiState: HomeUiState) {
        with(binding) {
            productsRecycler.hide()
            bannersPager.hide()
            if (uiState.isLoading) {
                bannerLoadStateView.progressBar.show()
                productsLoadStateView.progressBar.show()
            } else if (uiState.errorMessage.isNotEmpty()) {
                bannerLoadStateView.messageTextView.show()
                bannerLoadStateView.tryAgainButton.show()
                bannerLoadStateView.messageTextView.text = uiState.errorMessage
                bannerLoadStateView.tryAgainButton.setOnClickListener { refreshData() }

                productsLoadStateView.messageTextView.text = uiState.errorMessage
                productsLoadStateView.tryAgainButton
                    .show()
                    .setOnClickListener { refreshData() }
            } else {
                productsRecycler.show()
                bannersPager.show()

                bannerLoadStateView.progressBar.gone()
                bannerLoadStateView.messageTextView.gone()
                bannerLoadStateView.tryAgainButton.gone()

                productsLoadStateView.progressBar.gone()
                productsLoadStateView.messageTextView.gone()
                productsLoadStateView.tryAgainButton.gone()
            }
        }
    }
}