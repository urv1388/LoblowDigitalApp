package com.loblowdigitalapp.network

import com.google.gson.GsonBuilder
import com.loblowdigitalapp.utility.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object {
        private val retrofitService by lazy {
            val mGson =
                    GsonBuilder()
                            .setLenient()
                            .create()
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(mGson))
                    .build()
        }

        val cartApi by lazy {
            retrofitService.create(APIService::class.java)
        }

    }


}