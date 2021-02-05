package com.loblowdigitalapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loblowdigitalapp.R
import com.loblowdigitalapp.adapter.MyItemRecyclerViewAdapter
import com.loblowdigitalapp.model.CartItem
import com.loblowdigitalapp.repo.AppRepository
import com.loblowdigitalapp.utility.NetworkUtil
import com.loblowdigitalapp.utility.Resource
import com.loblowdigitalapp.viewmodel.MainViewModel
import com.loblowdigitalapp.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var adapter: MyItemRecyclerViewAdapter
    lateinit var mainViewModel: MainViewModel
    val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyItemRecyclerViewAdapter(arrayListOf()) { cartItem ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("cartItem", cartItem as CartItem)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        getMyCart(null)
        buttonTryAgain.setOnClickListener {
            getMyCart(null)
        }
    }

    private fun getMyCart(view: View?) {
        hasError()
        if (NetworkUtil.hasDataNetwork(this)) {
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
                            hasError(true, "Error : $message")
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
            })
        } else {
            hasError(true, "It seems no internet connection!")
        }
    }

    private fun hideProgressBar() {
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }

    private fun hasError(isError: Boolean = false, errorMessage: String = "") {
        handler.post {
            if (isError) {
                textViewError.visibility = View.VISIBLE
                buttonTryAgain.visibility = View.VISIBLE
                textViewError.text = errorMessage
            } else {
                textViewError.visibility = View.GONE
                buttonTryAgain.visibility = View.GONE
            }
        }

    }
}