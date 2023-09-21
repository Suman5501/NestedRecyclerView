package com.example.creddemo.api

import com.example.creddemo.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET("/p68785/skuSections")
    suspend fun getItems() : Response<ResponseData>;
}