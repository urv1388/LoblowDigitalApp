package com.loblowdigitalapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.loblowdigitalapp.repo.AppRepository

class MainViewModel(app: Application,private val appRepository: AppRepository) : AndroidViewModel(app) {


}