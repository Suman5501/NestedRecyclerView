package com.example.creddemo.repository

import com.example.creddemo.api.RetrofitInstance
import com.example.creddemo.model.ResponseData
import com.example.creddemo.model.Section
import retrofit2.Response

class CategoryRepository {

    suspend fun getCategory() : Response<ResponseData> {
        return RetrofitInstance.api.getItems();
    }

    suspend fun getCategories() : List<Section>? {
        return RetrofitInstance.api.getItems().body()?.data;
    }
}