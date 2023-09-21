package com.example.creddemo.model

import com.google.gson.annotations.SerializedName

data class DisplayData(
    @SerializedName(value = "name")
    val name: String? = null,

    @SerializedName(value = "description")
    val description : String? = null,

    @SerializedName(value = "icon_url")
    val imageUrl: String? = null
)
