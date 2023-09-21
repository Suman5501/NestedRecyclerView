package com.example.creddemo.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Section (
    @SerializedName(value = "id")
    val id: String? = null,

    @SerializedName(value = "section_properties")
    val sectionProperties: SectionProperty
 )