package com.example.creddemo.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryItem(
    @SerializedName(value = "id")
    val id: String? = null,

    @SerializedName(value = "display_data")
    val displayData: DisplayData? = DisplayData()
)
