package com.onopry.online_store_test_task.presentation.cart

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.adapters.CartItemAdapter
import com.onopry.online_store_test_task.databinding.FragmentCartBinding
import com.onopry.online_store_test_task.presentation.main.SharedCartViewModel
import com.onopry.online_store_test_task.utils.BaseMoneyConverter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private val viewModel: SharedCartViewModel by activityViewModels()
    private lateinit var binding: FragmentCartBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)

        binding.closeButton.setOnClickListener { findNavController().popBackStack() }


        val cartItemAdapter = CartItemAdapter(BaseMoneyConverter.CartItem())
        val moneyConverter = BaseMoneyConverter.CartTotal()
        binding.catrItemsRecycler.adapter = cartItemAdapter
        binding.catrItemsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    cartItemAdapter.setCartItems(state.data?.basket ?: emptyList())
//                    binding.totalSumAmount.text = "$${state.data?.total ?: 0} us"
                    binding.totalSumAmount.text = moneyConverter.print(state.data?.total)

                    binding.deliveryAmount.text = state.data?.delivery ?: "N/A"
                }
            }
        }
    }
}