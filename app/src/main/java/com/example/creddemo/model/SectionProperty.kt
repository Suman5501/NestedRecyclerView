package com.example.creddemo.model

import com.google.gson.annotations.SerializedName

data class SectionProperty (
    @SerializedName(value = "title")
    val title: String? = null,

    @SerializedName(value = "items")
    val items: List<CategoryItem> = arrayListOf()
)
