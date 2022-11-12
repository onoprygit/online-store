package com.onopry.online_store_test_task.presentation.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.onopry.data.utils.debugLog
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.adapters.DetailsImageAdapter
import com.onopry.online_store_test_task.adapters.DetailsImageItemDecorator
import com.onopry.online_store_test_task.databinding.FragmentDetailsBinding
import com.onopry.online_store_test_task.presentation.main.SharedCartViewModel
import com.onopry.online_store_test_task.utils.gone
import com.onopry.online_store_test_task.utils.shortToast
import com.onopry.online_store_test_task.utils.show
import com.onopry.online_store_test_task.utils.showIfConditionOrGone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()
    private val cartSharedCartViewModel: SharedCartViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        binding.toolbar.toobarTitle.text = resources.getString(R.string.details_title)

        binding.toolbar.closeButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.toolbar.actionButton.setOnClickListener {
            findNavController().navigate(
                DetailsFragmentDirections.actionDetailsFragmentToCartFragment()
            )
        }

        val imagesAdapter = DetailsImageAdapter()
        setupCarousel(imagesAdapter)

        binding.colorGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.color1 -> debugLog(binding.color1.isSelected.toString() + binding.color2.isSelected.toString())
                R.id.color2 -> debugLog(binding.color1.isSelected.toString() + binding.color2.isSelected.toString())
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.isLoading) shortToast("Loading")
                    if (state.errorMessage.isNotEmpty()) shortToast("Error")

                    with(binding) {
                        productName.text = state.data?.title

                        contentTextCPU.text = state.data?.CPU
                        contentTextCamera.text = state.data?.camera
                        contentTextRam.text = state.data?.ssd
                        contentTextSd.text = state.data?.sd

                        color1.setBackgroundColor(
                            Color.parseColor(
                                state.data?.color?.get(0) ?: "#00000000"
                            )
                        )
                        color2.setBackgroundColor(
                            Color.parseColor(
                                state.data?.color?.get(1) ?: "#00000000"
                            )
                        )

                        capacity1.text = state.data?.sd
                        capacity2.text = state.data?.sd

                        imagesAdapter.setImageUrls(state.data?.images ?: emptyList())
                        buyActionButton.text = "${buyActionButton.text} + ${state.data?.price}"
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                cartSharedCartViewModel.cartItemsCount.collect { cartItemsCount ->
                    with(binding.toolbar.notificationBadge) {
                        if (cartItemsCount > 0) {
                            text = cartItemsCount.toString()
                            this.show()
                        } else this.gone()
                    }

                }
            }
        }

    }


    private fun setupCarousel(imagesAdapter: DetailsImageAdapter) {
        binding.productImages.adapter = imagesAdapter

        binding.productImages.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))
        }
        binding.productImages.setPageTransformer(pageTransformer)

        val itemDecoration = DetailsImageItemDecorator(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.productImages.addItemDecoration(itemDecoration)
        //        binding.productImages.currentItem = 1

        //        onImageChangeCallback(viewModel.uiState.value.data?.images?.size ?: 0)
    }

    private fun onImageChangeCallback(listSize: Int) {
        binding.productImages.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (binding.productImages.currentItem) {
                        listSize - 1 -> binding.productImages.setCurrentItem(1, false)
                        0 -> binding.productImages.setCurrentItem(listSize - 2, false)
                    }
                }
            }
        })
    }
}