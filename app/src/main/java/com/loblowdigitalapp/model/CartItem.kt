package com.loblowdigitalapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartItem(
    val code: String?,
    val image: String?,
    val name: String?,
    val price: String?,
    val type: String?
) : Parcelable