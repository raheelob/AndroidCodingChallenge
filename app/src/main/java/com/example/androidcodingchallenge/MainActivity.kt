package com.example.androidcodingchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.androidcodingchallenge.databinding.ActivityMainBinding
import com.example.androidcodingchallenge.utils.LoadingViewDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    var mDialog = LoadingViewDialog(this)
    fun showLoading() = mDialog.showDialog()
    fun hideLoading() = mDialog.hideDialog()
}