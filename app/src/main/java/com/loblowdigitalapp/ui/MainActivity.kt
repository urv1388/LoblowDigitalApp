package com.loblowdigitalapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.loblowdigitalapp.adapter.MyItemRecyclerViewAdapter
import com.loblowdigitalapp.databinding.ActivityMainBinding
import com.loblowdigitalapp.repo.AppRepository
import com.loblowdigitalapp.utility.Resource
import com.loblowdigitalapp.viewmodel.MainViewModel
import com.loblowdigitalapp.viewmodel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var adapter: MyItemRecyclerViewAdapter
    lateinit var mainViewModel: MainViewModel
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        mainViewModel.getCart().observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { cartResponse ->





//                        Intent(this@MainActivity, MainActivity::class.java).also {
//                            startActivity(it)
//                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(this, "Error : $message", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        activityMainBinding.progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        activityMainBinding.progress.visibility = View.VISIBLE
    }
}