package com.onopry.online_store_test_task.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onopry.domain.models.home.ProductCategory
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.databinding.ItemCategoryBinding

typealias OnCategorySelectListener = (id: Int) -> Unit

class CategoryAdapter(
    private val selectListener: OnCategorySelectListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoriesList = mutableListOf<ProductCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoriesList[position])
    }

    override fun getItemCount() = categoriesList.size

    fun setCategoriesList(list: List<ProductCategory>){
        categoriesList.clear()
        categoriesList.addAll(list)
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: ProductCategory) {
            with(binding) {
                //                categoryIcon.setImageResource()
                when (category.id) {
                    1001L -> categoryIcon.setImageResource(R.drawable.ic_category_phones)
                    1002L -> categoryIcon.setImageResource(R.drawable.ic_category_computers)
                    1003L -> categoryIcon.setImageResource(R.drawable.ic_category_health)
                    1004L -> categoryIcon.setImageResource(R.drawable.ic_category_books)
                    1005L -> categoryIcon.setImageResource(R.drawable.ic_category_computers)
                }
                categoryName.text = category.name
            }
        }
    }
}