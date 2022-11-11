package com.onopry.online_store_test_task.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onopry.domain.models.home.ProductBanner
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.databinding.ItemBannerBinding
import com.onopry.online_store_test_task.utils.showIfConditionOrHide

typealias onBannerClickListener = (bannerId: Int) -> Unit

class BannersAdapter(private val onBannerClickListener: onBannerClickListener) :
    RecyclerView.Adapter<BannersAdapter.BannerViewHolder>() {

    private val banners = mutableListOf<ProductBanner>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BannerViewHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(banners[position])
    }

    override fun getItemCount() = banners.size

    fun setBannerList(list: List<ProductBanner>){
        banners.clear()
        banners.addAll(list)
        // TODO: improve list item changes
        notifyDataSetChanged()
    }

    inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: ProductBanner) {
            with(binding) {
                root.setOnClickListener{ onBannerClickListener(banner.id) }

                bannerFlagNew.showIfConditionOrHide { banner.isNew }
                title.text = banner.title
                subtitle.text = banner.subtitle


                Glide.with(root.context)
                    .load(banner.picture)
                    .placeholder(R.drawable.placeholder_image)
                    .into(bannerImage)
            }
        }
    }
}
