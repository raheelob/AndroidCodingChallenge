package com.example.androidcodingchallenge.view.fragments

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcodingchallenge.MainActivity
import com.example.androidcodingchallenge.R
import com.example.androidcodingchallenge.databinding.ProductListingFragmentBinding
import com.example.androidcodingchallenge.view.adapters.ProductListingAdapter
import com.example.androidcodingchallenge.view.base.BaseFragment
import com.example.androidcodingchallenge.view.fragments.ProductDetailFragment.Companion.PRODUCT_ITEM
import com.example.androidcodingchallenge.view.viewmodel.ProductListingViewModel
import com.example.androidcodingchallenge.view.viewmodel.ProductListingViewModel.ShapesEvent.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListingFragment : BaseFragment<ProductListingFragmentBinding, ProductListingViewModel>(
    ProductListingFragmentBinding::inflate
) {
    private lateinit var mAdapter: ProductListingAdapter

    override val viewModel: ProductListingViewModel by activityViewModels()

    override fun initView(binding: ProductListingFragmentBinding, savedInstanceState: Bundle?) {
        initRecyclerView()
        initSwipeToRefresh()
        initAdapter()
    }

    override fun observeViewModel(viewModel: ProductListingViewModel) {
        viewModel.getProductsList()
        binding.viewModel = viewModel
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.tasksEvent.collect {
                when (it) {
                    is Available -> {
                        with(mAdapter) { submitList(it.availableProductsList) }
                    }

                    is NotAvailable -> {
                        with(mAdapter) { submitList(it.unavailableProductsList) }
                    }

                    is All -> {
                        with(mAdapter) { submitList(it.unFilteredList) }
                    }

                    is Error -> {
                        (activity as MainActivity).hideLoading()
                        binding.errorMessage = "unable to load the list"
                        binding.viewFlipper.displayedChild = 1
                    }

                    is Loading -> {
                        (activity as MainActivity).showLoading()
                        binding.viewFlipper.displayedChild = 2
                        mAdapter.submitList(null)
                    }

                    is Data -> {
                        (activity as MainActivity).hideLoading()
                        binding.viewFlipper.displayedChild = 0
                        mAdapter.submitList(it.data)
                    }

                    is Error401 -> {
                        (activity as MainActivity).hideLoading()
                        binding.errorMessage =
                            "unable to load the list because of Authentication error..."
                        binding.viewFlipper.displayedChild = 1
                    }
                }
            }
        }
    }

    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvProductsList.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            linearLayoutManager.orientation
        )
        binding.rvProductsList.addItemDecoration(dividerItemDecoration)
        binding.rvProductsList.adapter = mAdapter
    }

    private fun initSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getProductsList()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun initRecyclerView() {
        mAdapter = ProductListingAdapter(ProductListingAdapter.ProductItemClickListener {
            findNavController().navigate(
                R.id.action_FirstFragment_to_SecondFragment,
                bundleOf(PRODUCT_ITEM to it)
            )
        })
        binding.rvProductsList.adapter = mAdapter
    }
}