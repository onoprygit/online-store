package com.onopry.online_store_test_task.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.databinding.ItemDetailsImageBinding

class DetailsImageAdapter: RecyclerView.Adapter<DetailsImageAdapter.DetailsImageHolder>() {

    private val urls = mutableListOf<String>()

    fun setImageUrls(list: List<String>){
        if (list.isNotEmpty()){
            urls.clear()
//            urls.addAll(listOf(list.first() + list + listOf(list.last())))
            urls.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsImageHolder {
        val binding = ItemDetailsImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailsImageHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsImageHolder, position: Int) {
        val imageUrl = urls[position]
        holder.binding.image.let { imageView ->
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(imageView)
        }
    }

    override fun getItemCount() = urls.size

    inner class DetailsImageHolder(val binding: ItemDetailsImageBinding):RecyclerView.ViewHolder(binding.root)
}
