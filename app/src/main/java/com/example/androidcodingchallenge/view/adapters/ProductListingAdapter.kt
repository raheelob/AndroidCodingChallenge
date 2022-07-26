package com.example.androidcodingchallenge.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcodingchallenge.data.responses.models.ProductsItem
import com.example.androidcodingchallenge.databinding.ItemAvailableProductListBinding
import com.example.androidcodingchallenge.databinding.ItemUnavailableProductListBinding
import com.example.androidcodingchallenge.utils.ProductItemType

class ProductListingAdapter(
    private val clickListener: ProductItemClickListener
) :
    ListAdapter<ProductsItem, RecyclerView.ViewHolder>(Companion) {

    inner class ProductAvailableAdapterViewHolder(val binding: ItemAvailableProductListBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ProductUnavailbleAdapterViewHolder(val binding: ItemUnavailableProductListBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<ProductsItem>() {
        override fun areItemsTheSame(
            oldItem: ProductsItem,
            newItem: ProductsItem
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ProductsItem,
            newItem: ProductsItem
        ): Boolean = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ProductItemType.VIEW_TYPE_AVAILABLE.status -> {
                val binding = ItemAvailableProductListBinding.inflate(layoutInflater, parent, false)
                ProductAvailableAdapterViewHolder(binding)
            }
            ProductItemType.VIEW_TYPE_UNAVAILABLE.status -> {
                val binding =
                    ItemUnavailableProductListBinding.inflate(layoutInflater, parent, false)
                ProductUnavailbleAdapterViewHolder(binding)
            }
            else -> {
                val binding =
                    ItemUnavailableProductListBinding.inflate(layoutInflater, parent, false)
                ProductUnavailbleAdapterViewHolder(binding)
            }
        }
    }

    class ProductItemClickListener(val clickListener: (mItem: ProductsItem) -> Unit) {
        fun onClick(mItem: ProductsItem) = clickListener(mItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mItem = getItem(position)
        when (holder.itemViewType) {
            ProductItemType.VIEW_TYPE_AVAILABLE.status -> {
                with(holder as ProductAvailableAdapterViewHolder){
                    binding.productItem = mItem
                    binding.clickListener = clickListener
                    binding.executePendingBindings()
                }
            }

            ProductItemType.VIEW_TYPE_UNAVAILABLE.status -> {
                with(holder as ProductUnavailbleAdapterViewHolder){
                    binding.productItem = mItem
                    binding.clickListener = clickListener
                    binding.executePendingBindings()
                }
            }
            else -> {
                with(holder as ProductUnavailbleAdapterViewHolder){
                    binding.productItem = mItem
                    binding.clickListener = clickListener
                    binding.executePendingBindings()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val mItem: ProductsItem = getItem(position)
        return when (mItem.available) {
            true -> ProductItemType.VIEW_TYPE_AVAILABLE.status
            false -> ProductItemType.VIEW_TYPE_UNAVAILABLE.status
            else -> ProductItemType.VIEW_TYPE_AVAILABLE.status
        }
    }
}