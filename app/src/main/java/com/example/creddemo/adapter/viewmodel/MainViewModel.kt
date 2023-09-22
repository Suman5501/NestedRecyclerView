package com.example.creddemo.adapter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creddemo.model.ResponseData

import com.example.creddemo.repository.CategoryRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: CategoryRepository) : ViewModel() {

    var response: MutableLiveData<Response<ResponseData>> = MutableLiveData()

    fun getCategories(){
        viewModelScope.launch {
            val result = repository.getCategory()
            response.value = result

        }
    }

}