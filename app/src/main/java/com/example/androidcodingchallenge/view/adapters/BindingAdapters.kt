package com.example.androidcodingchallenge.view.adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.androidcodingchallenge.data.responses.models.Price
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {
    @BindingAdapter("imagePath")
    @JvmStatic
    fun loadImageUrl(imageView: AppCompatImageView, url: String?) {
        url?.let {
            Glide
                .with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }

    @BindingAdapter("imagePathById")
    @JvmStatic
    fun imagePathById(imageView: AppCompatImageView, url: Int?) {
        url?.let {
            Glide
                .with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }

    @BindingAdapter("productPrice")
    @JvmStatic
    fun setProductPrice(textView: AppCompatTextView, price: Price) {
        textView.text = "${price.value} ${price.currency}"
    }

    @BindingAdapter("productDate")
    @JvmStatic
    fun dateFromTimeStamp(textView: AppCompatTextView, timestamp: Long) {
        val sdf = SimpleDateFormat("dd.MM.yy")
        val netDate = Date(timestamp)
        val date = sdf.format(netDate)
        textView.text = date.toString()
    }

}