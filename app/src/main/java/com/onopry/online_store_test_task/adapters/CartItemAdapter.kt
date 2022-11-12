package com.onopry.online_store_test_task.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onopry.domain.models.cart.CartItem
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.databinding.CartItemBinding

class CartItemAdapter : RecyclerView.Adapter<CartItemAdapter.CartItemHolder>() {

    private val items = mutableListOf<CartItem>()

    fun setCartItems(list: List<CartItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context))
        return CartItemHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.tag
        with(holder.binding) {
            productName.text = item.title
            val itemPrice = item.price * countItems.text.toString().toInt()
            productPrice.text = "$$itemPrice us"

            Glide.with(holder.binding.root.context)
                .load(productImage)
                .placeholder(R.drawable.placeholder_image)
        }
    }

    class CartItemHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)
}