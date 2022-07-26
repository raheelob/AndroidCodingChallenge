package com.example.androidcodingchallenge.view.fragments

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.example.androidcodingchallenge.R
import com.example.androidcodingchallenge.databinding.FragmentAboutBinding
import com.example.androidcodingchallenge.view.adapters.BindingAdapters
import com.example.androidcodingchallenge.view.base.BaseFragment
import com.example.androidcodingchallenge.view.viewmodel.ProductListingViewModel

class AboutFragment :
    BaseFragment<FragmentAboutBinding, ProductListingViewModel>(FragmentAboutBinding::inflate) {

    override val viewModel: ProductListingViewModel by activityViewModels()

    override fun initView(binding: FragmentAboutBinding, savedInstanceState: Bundle?) {
        BindingAdapters.imagePathById( binding.imageViewForArchitecture, R.drawable.mvvm_arch)
    }

    override fun observeViewModel(viewModel: ProductListingViewModel) {}

}