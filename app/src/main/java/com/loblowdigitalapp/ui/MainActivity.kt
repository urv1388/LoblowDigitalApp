package com.loblowdigitalapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loblowdigitalapp.R
import com.loblowdigitalapp.adapter.MyItemRecyclerViewAdapter
import com.loblowdigitalapp.model.CartItem
import com.loblowdigitalapp.repo.AppRepository
import com.loblowdigitalapp.utility.Resource
import com.loblowdigitalapp.viewmodel.MainViewModel
import com.loblowdigitalapp.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var adapter: MyItemRecyclerViewAdapter
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyItemRecyclerViewAdapter(arrayListOf()) { cartItem ->
            Intent(this@MainActivity, MainActivity::class.java).apply {
                intent.putExtra("cartItem", cartItem as CartItem)
            }.also { startActivity(it) }
        }
        recyclerView.adapter = adapter
        mainViewModel.getCart().observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { cartResponse ->
                        cartResponse.cartResult?.let { adapter.addCartList(it) }
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
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }
}