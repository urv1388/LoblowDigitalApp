package com.loblowdigitalapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.loblowdigitalapp.R
import com.loblowdigitalapp.model.CartItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_scrolling.*

class DetailActivity : AppCompatActivity() {

    var cartItem: CartItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        cartItem = intent.getParcelableExtra("cartItem") as CartItem?
        setTitle(cartItem?.name)
        textViewPrice.text = "Product price is:  ${cartItem?.price}"
        textViewType.text = "Product Type is ${cartItem?.type}"
        Glide.with(this).load(cartItem?.image).into(item_detail_image)
    }
}