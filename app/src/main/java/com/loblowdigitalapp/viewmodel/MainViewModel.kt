package com.loblowdigitalapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.loblowdigitalapp.model.CartResponse
import com.loblowdigitalapp.repo.AppRepository
import com.loblowdigitalapp.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class MainViewModel(app: Application, private val appRepository: AppRepository) :
    AndroidViewModel(app) {
    fun getCart() = liveData(Dispatchers.IO) {
        delay(1000)
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = appRepository.getCart()))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}