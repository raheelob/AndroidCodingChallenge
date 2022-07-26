package com.example.androidcodingchallenge.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.androidcodingchallenge.R
import com.example.androidcodingchallenge.data.responses.models.ProductsItem
import com.example.androidcodingchallenge.databinding.ProductDetailFragmentBinding
import com.example.androidcodingchallenge.view.base.BaseFragment
import com.example.androidcodingchallenge.view.viewmodel.ProductListingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment :
    BaseFragment<ProductDetailFragmentBinding, ProductListingViewModel>(ProductDetailFragmentBinding::inflate) {

    override val viewModel: ProductListingViewModel by activityViewModels()

    override fun initView(binding: ProductDetailFragmentBinding, savedInstanceState: Bundle?) {
        arguments?.let {
            productsItem = arguments?.getParcelable(PRODUCT_ITEM)
        }
        productsItem?.let {
            binding.product = it
        }
        initClickListeners()
    }

    override fun observeViewModel(viewModel: ProductListingViewModel) {}

    companion object {
        const val PRODUCT_ITEM = "product_item"
    }

    private var productsItem: ProductsItem? = null

    private fun initClickListeners() {
        binding.tvFooter.setOnClickListener {
            findNavController().navigate(
                R.id.action_ProductDetailFragment_to_staticWebviewFragment
            )
        }
    }
}