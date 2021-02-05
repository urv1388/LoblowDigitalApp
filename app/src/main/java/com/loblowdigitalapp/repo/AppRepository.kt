package com.loblowdigitalapp.repo

import com.loblowdigitalapp.network.RetrofitBuilder

class AppRepository {
    suspend fun getCart() = RetrofitBuilder.cartApi.getCart()
}