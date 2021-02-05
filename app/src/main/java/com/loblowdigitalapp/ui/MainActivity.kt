package com.loblowdigitalapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.loblowdigitalapp.R
import com.loblowdigitalapp.repo.AppRepository
import com.loblowdigitalapp.viewmodel.MainViewModel
import com.loblowdigitalapp.viewmodel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        getCart()
    }

    private fun getCart() {



    }
}