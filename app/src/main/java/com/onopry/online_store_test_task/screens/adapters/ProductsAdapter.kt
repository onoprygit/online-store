package com.onopry.online_store_test_task.screens.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onopry.domain.models.home.Product
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.databinding.ItemProductBinding

typealias onProductClickListener = (productId: Int) -> Unit

class ProductsAdapter(
    private val clickListener: onProductClickListener
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private val products = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size

    fun setProductsList(list: List<Product>) {
        products.clear()
        products.addAll(list)
        // TODO: improve list item changes
        notifyDataSetChanged()
    }


    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding) {
                root.setOnClickListener { clickListener(product.id) }

                productName.text = product.title
                productPrice.text = product.discountPrice
                productEarlyPrice.text = product.priceWithoutDiscount
                productEarlyPrice.paintFlags =
                    productEarlyPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                if (product.isFavorites)
                    likeOnProductIcon.setImageResource(R.drawable.ic_like_filled)
                else
                    likeOnProductIcon.setImageResource(R.drawable.ic_like_empty)

                Glide.with(this.root)
                    .load(product.picture)
                    .placeholder(R.drawable.placeholder_image)
                    .into(productImage)
            }
        }
    }
}