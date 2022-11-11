package com.onopry.online_store_test_task.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.onopry.domain.models.home.ProductCategory
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.databinding.ItemCategoryBinding

typealias OnCategorySelectListener = (categoryId: Int) -> Unit

class CategoryAdapter(
    private val categorySelectListener: OnCategorySelectListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoriesList = mutableListOf<ProductCategory>()

    //    private var selectedCategoryId = SelectedCategoryId(current = -1, previous = -1)
    var selectedCategoryPos = 0
        private set
    var prevSelectedCategoryPos = -1
        private set

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
        val category = categoriesList[position]
        holder.bind(category, position)
        holder.binding.categoryIcon.setOnClickListener {
            selectedCategoryPos =
                position.also { prevSelectedCategoryPos = selectedCategoryPos }
            categorySelectListener(category.id)
            notifyItemChanged(selectedCategoryPos)
            notifyItemChanged(prevSelectedCategoryPos)
        }
    }

    override fun getItemCount() = categoriesList.size

    fun setCategoriesList(list: List<ProductCategory>) {
        categoriesList.clear()
        categoriesList.addAll(list)
    }

    inner class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: ProductCategory, pos: Int) {

            with(binding) {
                if (selectedCategoryPos == pos) {
                    setSelectedCategoryUiState(categoryIcon, category.id)
                } else {
                    setUnselectedCategoryUiState(categoryIcon, category.id)
                }

                categoryName.text = category.name
            }
        }
    }

    fun setUnselectedCategoryUiState(categoryIcon: ImageView, categoryId: Int) {
        categoryIcon.setBackgroundResource(R.drawable.bg_category_unselected)
        when (categoryId) {
            10001 -> categoryIcon.setImageResource(R.drawable.ic_category_phones_unselected)
            10002 -> categoryIcon.setImageResource(R.drawable.ic_category_computers_unselected)
            10003 -> categoryIcon.setImageResource(R.drawable.ic_category_health_unselected)
            10004 -> categoryIcon.setImageResource(R.drawable.ic_category_books_unselected)
            10005 -> categoryIcon.setImageResource(R.drawable.ic_category_computers_unselected)
        }
    }

    fun setSelectedCategoryUiState(categoryIcon: ImageView, categoryId: Int) {
        categoryIcon.setBackgroundResource(R.drawable.bg_category_selected)
        when (categoryId) {
            10001 -> categoryIcon.setImageResource(R.drawable.ic_category_phones_selected)
            10002 -> categoryIcon.setImageResource(R.drawable.ic_category_computers_selected)
            10003 -> categoryIcon.setImageResource(R.drawable.ic_category_health_selected)
            10004 -> categoryIcon.setImageResource(R.drawable.ic_category_books_selected)
            10005 -> categoryIcon.setImageResource(R.drawable.ic_category_computers_selected)
        }
    }
}
