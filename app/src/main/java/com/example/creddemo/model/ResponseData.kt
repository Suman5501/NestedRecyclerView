package com.example.creddemo.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName(value = "data")
    val data: List<Section> = arrayListOf()
)
