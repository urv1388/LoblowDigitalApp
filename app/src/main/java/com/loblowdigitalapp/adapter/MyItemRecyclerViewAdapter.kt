package com.loblowdigitalapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loblowdigitalapp.R
import com.loblowdigitalapp.model.CartItem


class MyItemRecyclerViewAdapter(
    private val arrayListCartItem: ArrayList<CartItem>, val adapterOnClick: (Any) -> Unit
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val TAG = "MyItemRecyclerViewAdapt"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayListCartItem[position]
        holder.item_name.text = item.name
        holder.item_type_name.text = item.price
        Glide.with(holder.item_image.context).load(item.image).into(holder.item_image)
        holder.itemView.setOnClickListener {
            adapterOnClick(item)
        }
    }

    override fun getItemCount(): Int = arrayListCartItem.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item_image: ImageView = view.findViewById(R.id.item_image)
        val item_name: TextView = view.findViewById(R.id.item_name)
        val item_type_name: TextView = view.findViewById(R.id.item_type_name)

        override fun toString(): String {
            return super.toString() + " '" + item_name + "'"
        }
    }

    fun addCartList(cartList: ArrayList<CartItem>) {
        Log.d(TAG, "addCartList: $cartList")
        this.arrayListCartItem.apply {
            clear()
            addAll(cartList)
            notifyDataSetChanged()
        }
    }

    private fun sortElement() {
        this.arrayListCartItem.apply {
            sortByDescending { it.name }
            notifyDataSetChanged()
        }
    }


}