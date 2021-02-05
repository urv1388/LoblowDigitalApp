package com.loblowdigitalapp.model

import com.google.gson.annotations.SerializedName


data class CartResponse(
        @SerializedName("entries")
        val cartResult: ArrayList<CartItem>?
)